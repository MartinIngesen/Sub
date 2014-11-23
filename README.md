# Sub - The CLI-Subscene

[![Build Status](https://travis-ci.org/MartinIngesen/Sub.svg?branch=master)](https://travis-ci.org/MartinIngesen/Sub)

## What is Sub?
Sub is a CLI-command for downloading subtitles automagically from Subscene.com.

## How do I use it?

The base command works like this ```sub <video-file-name> [--pick]```.

The ```--pick``` flag allows you to get a list of the 5 top results, and pick which one to download.
Convenient when the command downloads the wrong file automatically.

You can run Sub using ```java -jar /path/to/sub_jar/sub-1.1.jar <video-file-name>``` in the directory where the file is present.

Or, to simplify things: add this to your zsh config: ```alias sub="java -jar /path/to/sub_jar/sub-1.1.jar "```

You may or may not need to add ```setopt completealiases``` to your .zshrc-file for autocompletion (tab) to work.

Navigate to your video via terminal, and run ```sub <video-file-name>```

## How does it work?
Sub uses Jsoup to parse Subscene's HTML. It automatically downloads the first result from Subscene (unless the ```-pick``` flag is set)
and unzips it to the directory you're in.

The script defaults to english subtitles.

## How do I build it?
The project uses Maven, and you can build it using ```mvn package```.

## Todo
* Add automatic search in directory for videofile.
* Add the possibility to supply a path to the sub-command.
* Commands for bash?
* Better error-handling
* Change languages
* Better automated recognition and result-filterning based on common identifiers in titles, such as "S01E12" or "112".
