# quotes
Quotes is a standalone Java GUI application (using Swing) that allows a user to search a collection of quotations. The data consists of a set of quotations with an associated author and topic. Quotes is released under an MIT license, and offers a number of options for searching:

* Wildcard searches (using '\*' and '%')
* Soundex (words that sound similar)
* Regular expression
* Contains

![Quotes](http://argonium.github.io/quotes.png)

The first two search modes reuse the code featured elsewhere on this web site. A wildcard search means the '\*' and '%' characters have a special meaning: '\*' means to match any string of consecutive characters (zero or more), and '%' means to match any one character. A Soundex search means to match on words that sound similar. A regular expression search means to allow the use of regular expressions in the search term. If you don't know what a regular expression is, don't use this option.

One useful feature of this application is the ability to search based on not just a word in the quotation, but also include a word or phrase from the quote's author.

There is currently no help file, but there is tooltip text for most of the controls, so the interface should be easy to understand. One possible source of confusion may be the two "Go" buttons on the Search page. The first one, under "Find by keyword", will cause the software to search for a match based on just the term entered by the user in either the quotation itself or its topic. This is the most common means of searching. The second "Go" button, under "Find by author", will cause the software to search for a match based on both the term entered in the first text field and the author name entered in the second text field. When searching by author, the text entered in the second text field (author) is checked as a simple substring of the author name stored in the data file.

To run the appication, build it via Ant ('ant clean dist'), and then open via 'java -jar quotes.jar' (or double-click quotes.jar). The data file is embedded in the jar file.

Part of the code is copyright JGoodies Karsten Lentzsch. This is limited to portions of the GUI.
