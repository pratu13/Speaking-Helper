#!/usr/bin/env python
# coding: utf-8

# In[2]:
import keras
import librosa
import numpy as np
import sys
import tensorflow
import os


# In[3]:


#from google.colab import drive
#drive.mount('/content/drive')


# In[4]:


class LivePredictions:
    """
    Main class of the application.
    """

    def __init__(self, file):
        """
        Init method is used to initialize the main parameters.
        """
        DIR_PATH = os.getcwd() + '\\src\\main\\resources\\'
        self.file = file
        MODEL_DIR_PATH = DIR_PATH + 'AudioData\\Save_model\\'
        #print( MODEL_DIR_PATH + 'Emotion_Voice_Detection_Model.h5' )
        self.path =  MODEL_DIR_PATH + 'Emotion_Voice_Detection_Model.h5'
        self.loaded_model = keras.models.load_model(self.path)

    def make_predictions(self):
        """
        Method to process the files and create your features.
        """
        data, sampling_rate = librosa.load(self.file)
        mfccs = np.mean(librosa.feature.mfcc(y=data, sr=sampling_rate, n_mfcc=40).T, axis=0)
        x = np.expand_dims(mfccs, axis=1)
        x = np.expand_dims(x, axis=0)
        #predictions = self.loaded_model.predict_classes(x)
        predict_x=self.loaded_model.predict(x) 
        classes_x=np.argmax(predict_x,axis=1)
        print( "Prediction is", " ", self.convert_class_to_emotion(classes_x))

    @staticmethod
    def convert_class_to_emotion(pred):
        """
        Method to convert the predictions (int) into human readable strings.
        """
        
        label_conversion = {'0': 'neutral',
                            '1': 'calm',
                            '2': 'happy',
                            '3': 'sad',
                            '4': 'angry',
                            '5': 'fearful',
                            '6': 'disgust',
                            '7': 'surprised'}

        for key, value in label_conversion.items():
            if int(key) == pred:
                label = value
        return label

if __name__ == '__main__':
    EXAMPLES_PATH = os.getcwd() + '\\src\\main\\resources\\AudioData\\examples\\'
    #live_prediction = LivePredictions(file=EXAMPLES_PATH + 'Neutral.wav')
    #live_prediction.loaded_model.summary()
    #live_prediction.make_predictions()
    #live_prediction = LivePredictions(file=EXAMPLES_PATH + 'Angry.wav')
    #live_prediction.make_predictions()
    #live_prediction = LivePredictions(file=EXAMPLES_PATH + '10-16-07-29-82-30-63.wav')
    #live_prediction.make_predictions()
    #live_prediction = LivePredictions(file=EXAMPLES_PATH + '03-01-05-02-02-02-01.wav')
    #live_prediction.make_predictions()
    #live_prediction = LivePredictions(file=EXAMPLES_PATH + '03-01-05-02-01-01-01.wav')
    #live_prediction.make_predictions()
    #live_prediction = LivePredictions(file=EXAMPLES_PATH + '03-01-08-01-02-02-01.wav')
    #live_prediction.make_predictions()
    #live_prediction = LivePredictions(file=EXAMPLES_PATH + 'i-cant-take-this.wav')
    #live_prediction.make_predictions()
    #live_prediction = LivePredictions(file=EXAMPLES_PATH + 'that-feels-really-powerful.wav')
    #live_prediction.make_predictions()
    print("Sentiment analysis for live audio:")
    #live_prediction = LivePredictions(file=EXAMPLES_PATH + 'liveaudio.wav')
    #live_prediction.make_predictions()
	live_prediction = LivePredictions(file)
	live_prediction.make_predictions()


# In[ ]:




