#!/usr/bin/env python
# coding: utf-8

# In[1]:


#get_ipython().run_line_magic('pylab', 'inline')
import pyaudio
import wave
import librosa
#import librosa.display
import os
import pandas as pd
import speech_recognition as sr
#get_ipython().run_line_magic('pylab', 'inline')
#import time

#time.sleep(2.4)

CHUNK = 1024 
FORMAT = pyaudio.paInt16 #paInt8
CHANNELS = 2 
RATE = 44100 #sample rate
RECORD_SECONDS = 5
DIR_PATH = os.getcwd() + '\\src\\main\\resources\\'
WAVE_OUTPUT_FILENAME = DIR_PATH + 'AudioData\\examples\\liveaudio.wav'


p = pyaudio.PyAudio()

stream = p.open(format=FORMAT,
                channels=CHANNELS,
                rate=RATE,
                input=True,
                frames_per_buffer=CHUNK) #buffer

print("* recording")

frames = []
for i in range(0, int(RATE / CHUNK * RECORD_SECONDS)):
    data = stream.read(CHUNK)
    frames.append(data) # 2 bytes(16 bits) per channel

print("* done recording")

stream.stop_stream()
stream.close()
p.terminate()

wf = wave.open(WAVE_OUTPUT_FILENAME, 'wb')
wf.setnchannels(CHANNELS)
wf.setsampwidth(p.get_sample_size(FORMAT))
wf.setframerate(RATE)
wf.writeframes(b''.join(frames))
wf.close()

data, sampling_rate = librosa.load(WAVE_OUTPUT_FILENAME)

#plt.figure(figsize=(15, 5))
#librosa.display.waveplot(data, sr=sampling_rate)

r = sr.Recognizer()
with sr.AudioFile(WAVE_OUTPUT_FILENAME) as source:
    # listen for the data (load audio to memory)
    audio_data = r.record(source)
    # recognize (convert from speech to text)
    text = r.recognize_google(audio_data)
    print('Speech to text:\n' + '-'*17)
    print(text)


# In[ ]:




