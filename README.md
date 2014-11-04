# Sub - The CLI-Subscene

## What is Sub?
Sub is a CLI-command for downloading subtitles automagically from Subscene.com.

## How do I use it?

You can run Sub using ```java -jar /path/to/sub_jar/sub-1.0.jar <video-file-name``` in the directory where the file is present.

Or, to simplify things: add this to your zsh config: ```alias sub="java -jar /path/to/sub_jar/sub-1.0.jar "```

You may or may not need to add ```setopt completealiases``` to your .zshrc-file for autocompletion to work.

Navigate to your video via terminal, and run ```sub <video-file-name>```

## How does it work?
Sub uses Jsoup to parse Subscene's HTML. It the downloads the first result and unzips it to the directory.

The script defaults to english subtitles.

## How do I build it?
The project uses Maven, and you can build it using ```mvn package```.

## Todo
* Add automatic search for videofile.
* Add the possibility to supply a path to the sub-command.
* Commands for bash?
* Better error-handling
* Possibility to select sub-file self?
* Change languages