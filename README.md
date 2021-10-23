# Videoh

Videoh is a java application that allows professors to upload video lessons and students to watch them. These latter can also leave some interactions (understood, not understood) and questions that the professor can then analyze to get a general idea of the level of comprehension of the lesson.

## Installation

The application uses [vlcj library](https://capricasoftware.co.uk/projects/vlcj-3/tutorials/installation) to play videos. This requires that the application [VLC](https://www.videolan.org/vlc/index.it.html) is installed on the same machine where Videoh is launched. Make sure to install it, otherwise no video will play.  
Vlcj library and all its dependency may not be included in the project. There's a zip file (downloaded from the official website of Vlcj) in this directory containing all the dependency that should be installed if missing: vlcj-3.12.1.jar, jna-platform-5.2.0.jar, jna-5.2.0.jar and slf4j-api-1.7.25.jar. If while launching Videoh the compiler won't regonize some classes, or will prompt some errors regarding missing dependencies, check if all these files are present in the project settings.  
Note that vlcj may prompt a few errors in the console tab while rendering the videos: just ignore them since they're related to the mismatch between the settings we're giving to the video and the actual video characteristics (i.e. codec, resolution or dimension of the video).  


## Usage
### Preparation - Professor side
The application comes with a readymade database. It won't store any video at the beginning: you will upload one on the first usage. 
To do this, log in as a professor  with these credentials:
>'prof1' as username  
>'pass1' as password   
  
Once logged in as professor1 push the button **Add New Video**.  
Then insert a title, a description, load a Thumbnail and finally the actual video file.  
The video file format **must be** an mp4 file. In this directory you will find a zip folder with a fiew videos and thumbnails that you can use for testing.  
Once the video has been uploaded, the application will show again the video list, that now contains the video just uploaded. The application will generate a **code** for this video, you can see it at the bottom right corner of the video row: copy it, you will need it on the next step.  

### Preparation -  Student side
Now, to test the student side push the Logout Button and Log as a student: select the username that you prefer, and on the following page paste the **video code** you copyed before.   
The video will be now played.   
You can here post - as a student would - whatever interaction (positive, negative) or ask any question you want. For testing purposes, try to post a few interactions and a few questions.  
Note that the green/red/yellow (positive, negative, question respectively) pins in the interaction panel are draggable, if you want to move them on a different time! Moreover, you can delete both interactions and questions by pushing the Trash Button or edit the questions by pushing the Review Questions button on the right panel.    
Once you're done, click on End Video or simply Logout.

### Statistics of the video
In order to take a look at the Statistics for the video, follow again the above steps to Log as professor1; then, push the See Statistics button. The Statistics Pane will be shown:
- at the bottom of the frame you will see a rectangle under the video slider: it's the interaction panel where all the interaction will be shown;
- at the center of the screen you will see on the left the video and on the right a summary of all the interactions/questions that all the students have posted;
- under the summary you can see the Show Interactions and Questions button: by pushing it the interaction panel will be populated with all the interactions that students have posted and a list of Question panels will be shown under it.
   
You are now ready to use the application.




## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.  

---

## Rights
This is an original work by Federica Bucchieri and Andrew Amato from University Paris Saclay. It is deliberately inspired by Evoli, a project developed by the IT team of Politecnico di Milano.
