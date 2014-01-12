---
layout: post
title: "Wirth, Oberon and Simplicity"
description: ""
comments: true
categories: [simplicity]
date: 2013-04-25
---
Today I read a very interesting bit of computing science history, about Niklaus Wirth and the Oberon language/system: ['Oberon - The Overlooked Jewel'](https://www.ics.uci.edu/~franz/Site/pubs-pdf/BC03.pdf).

I've never written any Pascal - back in my school days, we were writing [COMAL](http://en.wikipedia.org/wiki/COMAL) on the BBC Micro (for some strange reason), when other kids would have been using Pascal.  I'd heard of it of course, believing it to be a more structured Basic.  I'd heard of Niklaus Wirth as the creator of Pascal, and I'd heard of Modula and Oberon, but I really didn't know much more than that.

It's quite understandable then, that the paper begins by describing with regret how Wirth is known as the creator of Pascal and as a 'language and compiler guy' to the detriment of his other claims to fame.

The description of the Oberon system is fascinating, but what interested me in particular was the discussion of Wirth's determined efforts towards simplicity.  The comparison of Wirth's Modula-2 and Oberon compilers against more complex compilers reminds me of [Go](http://golang.org/) with its incredibly fast compile times versus your typical modern day C++ compiler.

The use of self-compilation speed as a benchmark seems like a great way to target and then maintain a balance between complexity and the performance benefits of optimizations in a compiler.  Complex optimizations where the cost of optimization in terms of code complexity, outweigh the benefits of faster binaries could be clearly recognised with such a metric.  Perhaps there are more creative benchmarks we can use in other applications?

Another example of the struggle for simplicity is the description of Wirth removing a working high performance, elegant, but complex data structure from the compiler and replacing it with a linear list, despite the effort that had been spent on the more complex solution.  This data structure should have performed better, but the cost of its complexity was too high and the actual use case didn't play to its strengths.  The simpler solution turned out to be both smaller and faster in general use.

I'm a big believer in the benefits of simplicity over complexity in most cases, but it's rather hard to express it other than as instinct or experience.  The use of the self-compilation speed benchmark was able to highlight the cases where the complexity outweighed the benefit in a compiler, but at the moment I'm not sure of a good way to highlight these cases in a more general application.  There's still plenty of room for instinct and experience!
---
layout: post
title: "TODO: Wiki"
description: ""
comments: true
categories: [todo, wiki]
date: 2013-05-18
---
One of my preferred ways of procrastinating is thinking about organising todo lists (the programmer in me wrote 'TODO' there and had to go back and change it - disappointed it's not syntax highlighted here...).  I'd like to combine todo lists with more general notes, documents, my calendar, etc., and have it all versioned too.  Oh, and it should be cross platform and the data should be accessible and up to date from home, work or anywhere else.  And it should be secure.  I'd like to be able to do some spreadsheet-like data manipulation and graphing too.

Now if I want to do all that, I'll have to write something myself.  I could probably use something like [Evernote](http://evernote.com/) to get part way there, but I don't want to pay for a service I'd then feel locked into, and anyway it's not quite what I have in mind.

Ideally, I'd like to be able to do all this without having to install anything on the client, so this basically means a website.  Or a text file.  Or maybe some heavy customization of [Emacs org-mode](http://orgmode.org/)?  (That's assuming Emacs is already installed, so not really cheating...)

One possibility, which doesn't meet all the requirements, but is interesting all the same, is [TiddlyWiki](http://tiddlywiki.com/).  I've tried TiddlyWiki before, using it while trying to [Get Things Done](http://en.wikipedia.org/wiki/Getting_Things_Done).  The idea is that you have a single html page, no server, providing a wiki, and any modifications get saved out to the same html page.  There's no versioning, but rather than operating at the level of wiki 'pages', you create notes instead.  You can have several open for viewing or editing at once.

It all works pretty well, at least in Firefox with the [TiddlyFox](https://addons.mozilla.org/en-US/firefox/addon/tiddlyfox/) extension to enable TiddlyWiki to save itself.  I had issues using Chrome on my Mac, so stuck to Firefox.

For whatever reason, I didn't use it for very long, but recently I noticed that a rewrite is in progress, so took another look.  [TiddyWiki5](http://five.tiddlywiki.com/) can be used without a server as before, or served by node.js.  It's alpha, and I've just started using it, so it's way too early to comment much on it, but it looks promising.  Using it as a standalone HTML file on Chrome still seems to have issues, and unfortunately Firefox still needs the TiddlyFox extension to work well.  You also need to remember to save before quitting.

Anyway, this post was a bit more rambling than I intended (even moreso now...), but this is an area that interests me, so hopefully I can write a bit more about how I get on with TiddlyWiki5, maybe a bit about Ward Cunningham's [Smallest Federated Wiki](https://github.com/WardCunningham/Smallest-Federated-Wiki) (I saw an excellent presentation by him about his new wiki at QCon London earlier in the year), and maybe even a little about my own work in this area, if it ever gets far enough along to talk about.
---
layout: post
title: "Crimes Against Bank Balances"
description: ""
comments: true
categories: [3dprinter, makerbot, cupcake, reprap]
date: 2013-05-23
---
My wife recently said that I have a criminal record.  Of course, she didn't mean a *real* criminal record (I think)...

What we were talking about was my tendency of occasionally spending relatively large sums of money on rather frivolous purchases.  E.g., an expensive camera that doesn't get used much, or this:

![My Makerbot Cupcake](/images/makerbot_cupcake.jpg)

I bought a [Makerbot Cupcake](http://en.wikipedia.org/wiki/MakerBot_Industries#Cupcake_CNC) in April 2010.  I played around with it a bit, but got a bit disheartened with the fiddliness and what I felt was a lack of precision in the prints.  For the last 3 years it's been languishing in a cupboard.

Recently I've been maybe feeling a little guilty about my poor Cupcake.  A little more pertinently, I've also been looking at videos of newer [RepRap](http://reprap.org) models and fancy building one from scratch.  So in the hope of building some of the plastic parts for a shiny new [Mendel](http://reprap.org/wiki/Mendel) or perhaps a  [Huxley](http://reprap.org/wiki/Huxley), I've dug out the old one and fired it up.  All the components seem to still work, but I haven't printed anything with it yet.  Hopefully tonight...
---
layout: post
title: "First Print"
description: ""
comments: true
categories: [3dprinter, makerbot, cupcake]
date: 2013-05-25
---
After much fiddling (and wailing, and gnashing of teeth, and crashing of build platform) I've managed to get the old Cupcake to complete its first print in about 3 years: a (shrunken) [door stop](http://www.thingiverse.com/thing:2154).  It's too small to be useful for our doors, but I'm quite happy with it as a first print.

It took a few hours of trial and error to get from the stage of being able to test the individual axes and the nozzle temperature to getting a complete print.  Most of the time was spent trying to find the appropriate version of [ReplicatorG](http://replicat.org/) and the motherboard firmware.

Of course my first try was with the latest and greatest ReplicatorG (040) and the latest version of the firmware that looked like it might work.  It didn't.  I could move the axes or change the temprature, but trying to build would hang ReplicatorG at the build estimation stage.  After a search, I found a suggestion of replacing all the E&lt;Num&gt; instances in the gcode with A&lt;Num&gt;, but this didn't work.

After a bit more fiddling, and still no success, I tried [Makerware](http://www.makerbot.com/makerware/), by Makerbot.  I've not being paying much attention to the 3D printer world, but it seems that Makerbot have pretty much abandoned the basic Cupcake.  Makerware doesn't support it, and they've also removed their wiki, which had all the build & setup instructions for their printers.  (You can [download it as an archive](http://www.makerbot.com/support/archive/), but that doesn't help when trying to search the web for anything in the wiki.)

I then went back in time, trying out increasingly older versions of ReplicatorG and the firmware.  I eventually settled on version 025 of ReplicatorG, and the oldest version of the firmware it provided, which eventually resulted in this:

![First Print: A (shrunken) door stop](/images/first_print.jpg)

I might try to upgrade everything again, until I find the latest version I can use, but I'm happy with this minimum for now.
---
layout: post
title: "procdump"
description: ""
comments: true
categories: [debugging]
date: 2013-06-18
---
[Procdump](http://technet.microsoft.com/en-us/sysinternals/dd996900) can be used to get a crash dump for an process in varying circumstances.

In my (test) case, my app is throwing NotImplementedException, and I want to create a crash dump when that is raised and not handled.  Once the process is running, and before the exception is raised, I hook procdump to my process using the following command:

`procdump -e -ma myapp.exe`

Note that myapp.exe is the name of the process - not the filepath.
---
layout: post
title: "Print Classpath in Clojure"
description: ""
comments: true
categories: [clojure]
date: 2013-07-06
---
Print out the current classpath, line by line, in Clojure:

``` clojure
(doseq [line (.getURLs (java.lang.ClassLoader/getSystemClassLoader))] (prn line))
```
---
layout: post
title: "ChanChan"
description: ""
comments: true
categories: [clojure, chanchan, jekyll]
date: 2013-07-07
---
I just spent the last 30 mins trying to get the Clojure code below to be nicely syntax highlighted in Jekyll. It turned out to require me to use the tag `highlight clojure` to mark the code to be highlighted.  Once that was done, it would silently fail to colour the text until I copied a css file for syntax highlighting that I had to download from somewhere, and put it in one of the several locations in my Jekyll/blog folder.

Not really the end of the world, but actually a bit comforting, since I decided to write my own static blog generator yesterday.

It's called ChanChan, named after my daughter's toy rabbit, because why not?  It's written in Clojure, because, again, why not?  Actually, there's more reason than that - I rather like Clojure, don't use it at work, and was looking for something to write with it.

It's very early days, but I plan on keeping a journal of the development here.  Currently, it assumes all posts are written in Markdown, and in the folder `assets/posts`.  It picks up all the `.md` files in that folder, converts them to html, and writes them to `site/posts`.

The code for this release can be found on [github](https://github.com/gmacd/chanchan/releases/tag/0.1), or you can see the the code in its entirity below.

``` clojure

(ns blog.core
  [:require [clojure.java.io :as jio]]
  [:use [markdown.core :only [md-to-html-string]]]
  [:use [hiccup core page]])

(def src-posts-path "assets/posts")
(def dest-posts-path "site/posts")

(defn html-post [title body]
  (html5 [:head
          [:link {:rel "stylesheet" :type "text/css" :href "../bootstrap/css/bootstrap.css"}]
          [:title title]]
         [:body body]))

(defn files-with-extension [src ext]
  "Return all files in src with the extension ext.  E.g. '.md'"
  (->> (jio/file src)
       (file-seq)
       (filter #(.endsWith (.getName %) ext))))

(defn with-ext [path ext]
  "Return a new file with the extension added or replaced with 'ext'"
  (let [period-idx (.lastIndexOf path ".")]
    (if (not= period-idx -1)
      (str (.substring path 0 period-idx) "." ext)
      (str path "." ext))))

(defn convert-md-file [src dest]
  (let [md-contents (slurp src)]
    (->> md-contents
         (md-to-html-string)
         (html-post "post?")
         (spit dest))))

(defn convert-all-md-files [src-path dest-path]
  (let [src (jio/file src-path)
        dest (jio/file dest-path)
        md-files (files-with-extension src-path ".md")]
    (doseq [md-file md-files]
      (let [dest-path (str dest-path "/" (with-ext (.getName md-file) "html"))]
        (convert-md-file md-file dest-path)))))

(defn -main [& args]
  ; Bit of a hack to create the folder - better way?
  (jio/make-parents (str dest-posts-path "/x"))
  (convert-all-md-files src-posts-path dest-posts-path))

```
---
layout: post
title: "ChanChan: File watching + web serving"
description: ""
comments: true
categories: [clojure, chanchan]
date: 2013-07-11
---
I've just comitted a new version of [ChanChan (0.2)](https://github.com/gmacd/chanchan/releases/tag/0.2) to github.  This relase is pretty small in terms of code changes, but adds some useful functionality:

* **Webserver**: ChanChan now uses ring to setup a webserver to serve the blog on port 3000.  There's currently no fallback if this port is unavailable.  To start the server, use `lein run`.
* **File watching**: ChanChan will watch the posts folder for any new or modified posts and convert them automatically.
---
layout: post
title: "ChanChan: Posts listed on front page"
description: ""
comments: true
categories: [clojure, chanchan]
date: 2013-07-15
---
A bit more work has been done on ChanChan, enough to merit a new tag - [0.3](https://github.com/gmacd/chanchan/releases/tag/0.3)!

The fundamental change is that ChanChan now shows a list of links on the front page to all the posts.  In making this change, quite a few of the functions have been modified, making it a bit easier to reuse useful data at different points in the post conversion pipeline.

A couple of random thoughts occured to be while making these changes:
* Clojure functions tend to be small.
* I have to put a lot of thought into how best to write the code, or how best to change it.  I've never written a static web page generator before, but I imagine I could knock one up in C# (the main language I use at work) pretty quickly.  I'm not sure if I'm taking more time due to my relative lack of familiarity with the language, or if it's a more general side-effect of functional or lisp programming.
* .Net has much better facilities for operating on paths.  Or maybe Java/Clojure has better facilities that I'm not aware of...
* I'm pleased with the size of the codebase.  It's currently running at 107 lines of code, with comments, docstrings and empty lines (HTML templates excluded).  I'm not actively trying to minimize the number of lines used, but I'd like to get a minimal useful site generator fitting comfortably into a single Clojure file without it feeling forced or being detrimental to understanding the code.

Looking forward, the next step is to add some metadata to the posts and begin to extract it.  I'm thinking of post titles initially, and then perhaps tags.  Unfortunately, Markdown doesn't seem to support metadata by default, although there appear to be some extensions that support this.  Time for some googling...
---
layout: post
title: "File attributes in Clojure"
description: ""
comments: true
categories: [clojure, chanchan]
date: 2013-07-19
---
While writing [ChanChan](https://github.com/gmacd/chanchan) I wanted to get the creation time of a file.  `java.io.File` only gives you the last modified time, which isn't quite what I was after.  Java 7 has `BasicFileAttributes`, from which you can access the creation time, last modified time, size, etc.  To get an instance of this interface, you need to call `Files.readAttributes`.  This turned out to be quite fiddly in Clojure (at least for me, a relative newcomer!), so I'm putting to here in case anyone else is looking for it.  (Hopefully the namespace is correct - it's been edited from my original code...).

``` clojure

(ns foo
  (:require [clojure.java.io :as jio])
  (:import (java.nio.file Files LinkOption)
           (java.nio.file.attribute BasicFileAttributes)))

(defn file-attributes [file]
  "Return the file BasicFileAttributes of file.  File can be a file or a string
   (or anything else acceptable to jio/file)"
  (Files/readAttributes (.toPath (jio/file file))
                        BasicFileAttributes
                        (into-array LinkOption [])))

```
---
layout: post
title: "Kossel First Post!"
description: ""
comments: true
categories: [kossel]
date: 2013-07-30
---
![Traxxas 5347](/images/traxxas.jpg)

I've decided to build a Kossel Mini delta 3D printer.  This is the first part that's been delivered.  It'll be a long time before I'll complete this, but I've ordered some of the parts, and I plan on keeping a build log here.

You can see more about the [Mini Kossel here](http://deltabot.tumblr.com).
---
layout: post
title: "Kossel Second Post!"
description: ""
comments: true
categories: [kossel]
date: 2013-07-31
---
So it's now day 2 of the Mini Kossel build and I have....  nuts and bolts:

![Nuts+Bolts](/images/nuts-n-bolts.jpg)

Still rather a long way to go, but this should be all the nuts and bolts I need for the build (and then some!).
---
layout: post
title: "Orwell's Rules of Writing"
description: ""
comments: true
categories: [writing]
date: 2013-11-14
---

From '[Politics and the English Language](https://www.mtholyoke.edu/acad/intrel/orwell46.htm)'

1. Never use a metaphor, simile, or other figure of speech which you are used to seeing in print.

2. Never use a long word where a short one will do.

3. If it is possible to cut a word out, always cut it out.

4. Never use the passive where you can use the active.

5. Never use a foreign phrase, a scientific word, or a jargon word if you can think of an everyday English equivalent.

6. Break any of these rules sooner than say anything outright barbarous.
---
layout: post
title: "Random Opinions on Dart"
date: 2013-11-17 09:06
comments: true
categories: [dart]
published: true
---
A year or so ago, I took a look at Dart, Google's Javascript alternative (depending on how you look at it).  It didn't stick with me back then, but seeing Angular.dart made me want to try it out again, and I've been pretty pleased with what I've seen so far.

I hadn't used Angular.js before, but I've done a fair bit of WPF, and the concepts seem similar.  I'm not going to go into it in detail, but it seems to share some pros and cons with WPF - a powerful way of hooking a UI up to some controller code, but not much help with errors in the markup.

As for Dart, it seems a bit more polished these days - version 1.0 of the SDK was released a couple of days ago.  Language-wise, there's not much new, but TBH, given the intention seems to have been to create an accessible Javascript alternative, with none of the legacy craziness of js, that's not surprising.  Anyone familiar with C#, Java, Javascript or any number of mainstream languages won't find it too hard to pick up.

So here are some random things I've found out while writing a little toy in Dart (Conway's Game of Life):

- First of all, it's fun!  It's very accessible, with a nice rapid turnaround.
- It's much nicer than Javascript.  Modules!
- The IDE is pretty good, but it's still Eclipse, so some people will hate it.  I don't mind it too much - it's got a quick fix to auto-import, and a functioning debugger, which more than makes up for any shortfalls.  OTOH, the way it handles editor window tabbing (by default at least), is different to just about everything, and is also worse.
- There's a typedef keyword, but it only seems to apply to function types.  Type aliases would be useful, but I haven't seen any yet.
- The standard library seems pretty good, and has nice LINQ-type functionality.
- No multi-dimensional arrays.  In fact, no arrays - just List<T>, which has the same terribly deceptive name as C#.  It's not a list, it's an array with some listy methods.
- You can create simple constructors very concisely, though I find it a little strange.  I'm sure I'll get used to it.
- To build your app for deployment, you run 'pub build'.  Unfortunately, this didn't work for me.  I think it's because the standard library and Angular.dart were throwing up warnings - they probably shouldn't.
---
layout: post
title: "Conway's Game of Life in Dart"
date: 2013-11-17 21:36
comments: true
categories: [dart, angular]
---
While playing around with Dart, I implemented [Conway's Game of Life](http://en.wikipedia.org/wiki/Conway%27s_Game_of_Life).  It was also a good excuse to try out Angular.

I haven't put a live demo up yet, but you can [grab the source here.](https://github.com/gmacd/dartlife)
---
layout: post
title: "Switched to/ Octopress"
date: 2013-11-17 00:29
comments: true
categories: [blog]
---
Switched from my own static blog generator, ChanChan, to Octopress.  Writing it was a nice exercise, but I don't want to spent any more time maintaining it!

Need to import my old posts and choose a better theme...
