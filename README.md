# Videoh

Videoh is a java application that allows professors to upload video lessons and students to watch them. These latter can also leave some interactions (understood, not understood) and questions that the professor can then analyze to get a general idea of the level of comprehension of the lesson.

## Installation

The application uses a vlcj library to play videos. This requires that the application [VLC](https://www.videolan.org/vlc/index.it.html) is installed on the same machine where Videoh is launched.
Make sure to install it, otherwise no video will play.

## Usage
### Preparation - Professor side
The application comes with a readymade database. It won't store any video at the beginning. This will be uploaded at the first usage. 
To do this, log in on the professor side with these credentials:
>'prof1' as username  
>'pass1' as password   
  
Once logged in as professor1 push the button **Add New Video**.  
Then insert a title, a description, load a Thumbnail and finally the actual video file.  
The video file format **must be** an mp4 file.  
Once the video has been uploaded the application will show again the video list, that now contains the video just uploaded. The application will generate a **code** for this video, you can see it at the bottom right corner of the video row: copy it.  

### Preparation -  Student side
Now, to test the student side push the Logout Button and Log as a students: select the username that you prefer, and on the following page paste the **video code** you copyed before.   
The video will be now played.   
You can here post - as a student would - whatever interaction (positive, negative) or ask any question. For testing purposes, try to post a few interactions and a few questions: note that the green/red pins in the interaction panel are draggable!  
Once you're done, click on End Video or simply Logout.

### Statistics of the video
In order to take a look at the Statistics for the video, follow again the above steps to Log as professor one; then, push the See Statistics button. The Statistics Pane will be shown:
- at the bottom of the frame you will see a rectangle under the video slider: it's the interaction panel where all the interaction will be showed;
- at the center of the screen you will see on the left the video and on the right a summary of all the interaction that all the students have posted;
- under the summary you can see the Show Interactions and Questions button: by pushing it the interaction panel will be populated with all the interactions that students have posted and a list of Question panels will be shown under it.
   
You are now ready to use the application.




## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.
