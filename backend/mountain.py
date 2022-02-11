#!/usr/local/bin/python3
#
# Authors: [PLEASE PUT YOUR NAMES AND USER IDS HERE]
#
# Mountain ridge finder
# Based on skeleton code by D. Crandall, April 2021
#

from PIL import Image
from numpy import *
from scipy.ndimage import filters
import sys
import imageio

# calculate "Edge strength map" of an image
#
def edge_strength(input_image):
    grayscale = array(input_image.convert('L'))
    filtered_y = zeros(grayscale.shape)
    filters.sobel(grayscale,0,filtered_y)
    return sqrt(filtered_y**2)

# draw a "line" on an image (actually just plot the given y-coordinates
#  for each x-coordinate)
# - image is the image to draw on
# - y_coordinates is a list, containing the y-coordinates and length equal to the x dimension size
#   of the image
# - color is a (red, green, blue) color triple (e.g. (255, 0, 0) would be pure red
# - thickness is thickness of line in pixels
#
def draw_edge(image, y_coordinates, color, thickness):
    for (x, y) in enumerate(y_coordinates):
        for t in range( int(max(y-int(thickness/2), 0)), int(min(y+int(thickness/2), image.size[1]-1 )) ):
            image.putpixel((x, t), color)
    return image
# def find_transition_probability(edge_strength):
def maxium_select(cal,row,col,edge_strength):
    max_value=-sys.maxsize
    max_row=0
    # col_len=len(edge_strength)
    for k in range(0,len(edge_strength)):
        # change default value
        # p=cal[k][col-1]* (((col_len-abs(k-row)))/col_len)
        # p=cal[k][col-1]* (((col_len-(abs(k-row)*10)))/col_len)

        # p=cal[k][col-1]* ((140-abs(k-row))/140)
        # p=cal[k][col-1]+ (math.log(col_len-(abs(k-row)))-math.log(col_len))
        p=cal[k][col-1]+ (-1*((k-row)**2)/100)
        if(p>max_value):
            max_value=p
            max_row=k
    return [max_value,max_row]

# def maxium_select_opp(cal,row,col,edge_strength):
#     max_value=-sys.maxsize
#     max_row=0
#     # col_len=len(edge_strength)
#     for k in range(0,len(edge_strength)):
#         # change default value
#         # p=cal[k][col-1]* (((col_len-abs(k-row)))/col_len)
#         # p=cal[k][col-1]* (((col_len-(abs(k-row)*10)))/col_len)

#         # p=cal[k][col-1]* ((140-abs(k-row))/140)
#         # p=cal[k][col-1]+ (math.log(col_len-(abs(k-row)))-math.log(col_len))
#         p=cal[k][col+1]+ (-1*((k-row)**2)/16)
#         if(p>max_value):
#             max_value=p
#             max_row=k
#     return [max_value,max_row]

def cal_relative_strength(edge_strength,col,row):
    known_edge_stregth=edge_strength[row][col]
    cols=len(edge_strength[0])
    rows=len(edge_strength)
    new_edge_str=[[0 for i in range(0,cols)] for j in range(0,rows)]
    next_col_strengths=[]
    
    # for i in range(0,len(edge_strength[0])):
    #     next_col_strengths[i]=1-(abs(edge_strength[i][y+1]-known_edge_stregth)/known_edge_stregth)
    for j in range(0,cols):
        for i in range(0,rows):
            new_edge_str[i][j]=1-((abs(edge_strength[i][j]-known_edge_stregth))/1000)
            # new_edge_str[i][j]=(-1*((edge_strength[i][j]-known_edge_stregth)**2)/9)
            # if(new_edge_str[i][j]<=0):
            #     new_edge_str[i][j]=0.00000000000000000001
    for i in range(0,rows):
        if(i==row):
            new_edge_str[i][col]=1
        else:
            new_edge_str[i][col]=0.00000000000000000001
    return new_edge_str
def veterbi_mountain_points_new(col,row,edge_strength,max_col):
    list_row=[]
    new_edge_str=cal_relative_strength(edge_strength,col,row)
    cols=len(edge_strength[0])
    rows=len(edge_strength)
    cal=[[0 for i in range(0,cols)] for j in range(0,rows)]
    selected_row=[[0 for i in range(0,cols)] for j in range(0,rows)]
    for i in range(0,rows):
        # cal[i][0]=new_edge_str[i][0]
        cal[i][0]=math.log(new_edge_str[i][0])
        # cal[i][0]=edge_strength[i][0]/(max_col[0])
        # cal[i][0]=log(edge_strength[i][0])-log(max_col[0])
    # transition_probability= find_transition_probability(edge_strength)
    for j in range(1,cols):
        for i in range(0,rows):
            [max_value,max_row]=maxium_select(cal,i,j,edge_strength)
            selected_row[i][j]=max_row
            cal[i][j]=math.log(new_edge_str[i][j])+max_value
            # cal[i][j]=(edge_strength[i][j]/(max_col[j]))*max_value
            # cal[i][j]=log(edge_strength[i][j])-log(max_col[j])+max_value

    # print(cal)
    max_value=cal[0][cols-1]
    max_row=0
    for k in range(1,rows):
        if(max_value<cal[k][cols-1]):
            max_value=cal[k][cols-1]
            max_row=k
    list_row.append(max_row)
    k=max_row
    # print("----")
    for i in range(cols-1,0,-1):
        list_row.append(selected_row[k][i])
        k=selected_row[k][i]
        # print(k,end=" ")
    # print("-----------")
    list_row.reverse()
    return list_row

def get_probability_edge_strength(edge_strength,max_col):
    cols=len(edge_strength[0])
    rows=len(edge_strength)
    prob=[[0 for i in range(0,cols)] for j in range(0,rows)]
    for j in range(0,cols):
        for i in range(0,rows):
            prob[i][j]=edge_strength[i][j]/(max_col[j])
    return prob
def veterbi_mountain_points_simple(col,row,edge_strength,max_col):
    list_row=[]
    cols=len(edge_strength[0])
    rows=len(edge_strength)
    cal=[[0 for i in range(0,cols)] for j in range(0,rows)]
    selected_row=[[0 for i in range(0,cols)] for j in range(0,rows)]
    edge_strength_prob=get_probability_edge_strength(edge_strength,max_col)

    # maximum emission probability to the coordinate and rest all are assigned to minimum value 
    for i in range(0,rows):
        if(i==row):
            edge_strength_prob[i][col]=1-0.000000000000000000000000000001
        else:
            edge_strength_prob[i][col]=0.000000000000000000000000000001

    # initial state in viterbi
    for i in range(0,rows):
        cal[i][0]=math.log(edge_strength_prob[i][0])
        # cal[i][0]=edge_strength[i][0]/(max_col[0])
        # cal[i][0]=log(edge_strength[i][0])-log(max_col[0])
    # transition_probability= find_transition_probability(edge_strength)
    
    # viterbi algo
    for j in range(1,cols):
        for i in range(0,rows):
            [max_value,max_row]=maxium_select(cal,i,j,edge_strength)
            selected_row[i][j]=max_row
            cal[i][j]=math.log(edge_strength_prob[i][j])+max_value
            # cal[i][j]=(edge_strength[i][j]/10000)*max_value
            # cal[i][j]=log(edge_strength[i][j])-log(max_col[j])+max_value

    # print(cal)
    max_value=cal[0][cols-1]
    max_row=0
    for k in range(1,rows):
        if(max_value<cal[k][cols-1]):
            max_value=cal[k][cols-1]
            max_row=k
    list_row.append(max_row)
    k= max_row
    for i in range(cols-1,0,-1):
        list_row.append(selected_row[k][i])
        k=selected_row[k][i]
    list_row.reverse()
    return list_row

# def two_veterbi_mountain_points_simple(col,row,edge_strength,max_col):
#     list_row1=[]
#     list_row2=[]
#     cols=len(edge_strength[0])
#     rows=len(edge_strength)
#     cal=[[0 for i in range(0,cols)] for j in range(0,rows)]
#     selected_row=[[0 for i in range(0,cols)] for j in range(0,rows)]
#     edge_strength_prob=get_probability_edge_strength(edge_strength,max_col)

#     # maximum emission probability to the coordinate and rest all are assigned to minimum value 
#     for i in range(0,rows):
#         if(i==row):
#             edge_strength_prob[i][col]=1-0.000000000000000000000000000001
#         else:
#             edge_strength_prob[i][col]=0.000000000000000000000000000001

#     # initial state in viterbi
#     for i in range(0,rows):
#         cal[i][col]=math.log(edge_strength_prob[i][col])
#         # cal[i][0]=edge_strength[i][0]/(max_col[0])
#         # cal[i][0]=log(edge_strength[i][0])-log(max_col[0])
#     # transition_probability= find_transition_probability(edge_strength)
    
#     # viterbi algo
#     for j in range(col+1,cols):
#         for i in range(0,rows):
#             [max_value,max_row]=maxium_select(cal,i,j,edge_strength)
#             selected_row[i][j]=max_row
#             cal[i][j]=math.log(edge_strength_prob[i][j])+max_value
#             # cal[i][j]=(edge_strength[i][j]/10000)*max_value
#             # cal[i][j]=log(edge_strength[i][j])-log(max_col[j])+max_value

#     # print(cal)
#     max_value=cal[0][cols-1]
#     max_row=0
#     for k in range(1,rows):
#         if(max_value<cal[k][cols-1]):
#             max_value=cal[k][cols-1]
#             max_row=k
#     list_row1.append(max_row)
#     k= max_row
#     for i in range(cols-1,col,-1):
#         list_row1.append(selected_row[k][i])
#         k=selected_row[k][i]
#     list_row1.reverse()
#     list_row1.pop(0)
#     # return list_row

#     # other side
#     for j in range(col-1,-1,-1):
#         for i in range(0,rows):
#             [max_value,max_row]=maxium_select_opp(cal,i,j,edge_strength)
#             selected_row[i][j]=max_row
#             cal[i][j]=math.log(edge_strength_prob[i][j])+max_value
#             # cal[i][j]=(edge_strength[i][j]/10000)*max_value
#             # cal[i][j]=log(edge_strength[i][j])-log(max_col[j])+max_value

#     # print(cal)
#     max_value=cal[0][0]
#     max_row=0
#     for k in range(1,rows):
#         if(max_value<cal[k][0]):
#             max_value=cal[k][0]
#             max_row=k
#     list_row2.append(max_row)
#     k= max_row
#     for i in range(0,col):
#         list_row2.append(selected_row[k][i])
#         k=selected_row[k][i]
#     list_row2.extend(list_row1)
#     return list_row2







# def veterbi_mountain_points(col,row,edge_strength,max_col):
#     list_row=[]
#     new_edge_str=cal_relative_strength(edge_strength,col,row)
#     cols=len(edge_strength[0])
#     rows=len(edge_strength)
#     cal=[[0 for i in range(0,cols)] for j in range(0,rows)]
#     selected_row=[[0 for i in range(0,cols)] for j in range(0,rows)]
#     for i in range(0,rows):
#         cal[i][col]=new_edge_str[row][col]
#         # cal[i][0]=edge_strength[i][0]/(max_col[0])
#         # cal[i][0]=log(edge_strength[i][0])-log(max_col[0])
#     # transition_probability= find_transition_probability(edge_strength)
#     for j in range(col+1,cols):
#         for i in range(0,rows):
#             if(j==col+1):
#                 selected_row[i][j]=row
#                 cal[i][j]=(new_edge_str[i][j])
#             else:
#                 [max_value,max_row]=maxium_select(cal,i,j,edge_strength)
#                 selected_row[i][j]=max_row
#                 cal[i][j]=(new_edge_str[i][j])*max_value
#             # cal[i][j]=(edge_strength[i][j]/(max_col[j]))*max_value
#             # cal[i][j]=log(edge_strength[i][j])-log(max_col[j])+max_value

#     # print(cal)
#     max_value=cal[0][cols-1]
#     max_row=0
#     for k in range(1,rows):
#         if(max_value<cal[k][cols-1]):
#             max_value=cal[k][cols-1]
#             max_row=k
#     list_row.append(max_row)
#     k=max_row
#     print("----")
#     for i in range(cols-1,0,-1):
#         list_row.append(selected_row[k][i])
#         k=selected_row[k][i]
#         print(k,end=" ")
#     print("-----------")
#     list_row.reverse()
#     return list_row


def veterbi_mountain(max_col,edge_strength):
    list_row=[]
    cols=len(edge_strength[0])
    rows=len(edge_strength)
    cal=[[0 for i in range(0,cols)] for j in range(0,rows)]
    selected_row=[[0 for i in range(0,cols)] for j in range(0,rows)]
    for i in range(0,rows):
        # cal[i][0]=edge_strength[i][0]/(max_col[0])
        # cal[i][0]=edge_strength[i][0]/(max_col[0])
        cal[i][0]=math.log(edge_strength[i][0])-math.log(max_col[0])
    # transition_probability= find_transition_probability(edge_strength)
    for j in range(1,cols):
        for i in range(0,rows):
            [max_value,max_row]=maxium_select(cal,i,j,edge_strength)
            selected_row[i][j]=max_row
            cal[i][j]=(math.log(edge_strength[i][j])-math.log(max_col[j]))+max_value
            # cal[i][j]=(edge_strength[i][j]/10000)*max_value
            # cal[i][j]=log(edge_strength[i][j])-log(max_col[j])+max_value

    max_value=cal[0][cols-1]
    max_row=0
    for k in range(1,rows):
        if(max_value<cal[k][cols-1]):
            max_value=cal[k][cols-1]
            max_row=k
    list_row.append(max_row)
    k= max_row
    for i in range(cols-1,0,-1):
        list_row.append(selected_row[k][i])
        k=selected_row[k][i]
    list_row.reverse()
    return list_row

# main program
#
gt_row = -1
gt_col = -1
if len(sys.argv) == 2:
    input_filename = sys.argv[1]
elif len(sys.argv) == 4:
    (input_filename, gt_row, gt_col) = sys.argv[1:]
else:
    raise Exception("Program requires either 1 or 3 parameters")
# (input_filename, gt_row, gt_col)= ("/Users/yaminipriyakodeboyina/Documents/MS/SEM-1/Elements of AI/Assignments/shchua-sruangsa-ykodeboy-a3/part2/test_images/mountain9.jpg", 82,119)
# load in image 
input_image = Image.open(input_filename)

# compute edge strength mask
edge_strength = edge_strength(input_image)
imageio.imwrite('edges.jpg', uint8(255 * edge_strength / (amax(edge_strength))))

# You'll need to add code here to figure out the results! For now,
# just create a horizontal centered line.
# simple posterior value -  red line
cal_ridege_red=[]
for col in range(0,len(edge_strength[0])):
    # cal_ridege.append(max(edge_strength,col))
    l=[_[col] for _ in edge_strength]
    cal_ridege_red.append(l.index(max(l)))

# assigning 0 to 0.00000000001, so that we can cal log for those values
for j in range(0,len(edge_strength[0])):
    for i in range(0,len(edge_strength)):
        if(edge_strength[i][j]==0):
            edge_strength[i][j]=0.00000000000000000001

# viterbi without points - blue line
cal_ridege_blue=veterbi_mountain(cal_ridege_red,edge_strength)


# cal_ridege_yellow=veterbi_mountain_points(gt_col,gt_row,edge_strength,cal_ridege_red)

#  viterbi with points - green line
# cal_ridege_green=veterbi_mountain_points_simple(int(gt_col),int(gt_row),edge_strength,cal_ridege_red)
cal_ridege_green=veterbi_mountain_points_new(int(gt_col),int(gt_row),edge_strength,cal_ridege_red)
    
# edges_image = Image.open('edges.jpg')
# edge_strength1 = edge_strength(edges_image)
# output answer
# print(cal_ridege_red)
# print(cal_ridege_blue)
# print(cal_ridege_green)
# print(cal_ridege_yellow)
# for i in range(0,len(edge_strength)):
#     print(edge_strength[i][cal_ridege_blue[i]],end=" ")
# imageio.imwrite("output9.jpg", draw_edge(input_image, ridge, (255, 0, 0), 3))
imageio.imwrite("output.jpg", draw_edge(input_image, cal_ridege_red, (255, 0, 0), 3))
imageio.imwrite("output.jpg", draw_edge(input_image, cal_ridege_blue, (0, 0, 255), 3))
imageio.imwrite("output.jpg", draw_edge(input_image, cal_ridege_green, (0, 255, 0), 3))
# imageio.imwrite("output.jpg", draw_edge(input_image, cal_ridege_yellow, (255, 255, 0), 3))
# imageio.imwrite("output.jpg", draw_edge(edges_image, cal_ridege, (255, 0, 0), 5))
