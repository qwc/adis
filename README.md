Releases
====
Upcoming and past releases. Everything below 1.0 is unstable/beta/alpha...

Upcoming release 0.2:
- Full functionality on Request lines
- Tests for Requests

Release 0.1:
Usable for Entity handling. 
- Register your handler
- Do something with it.

Description
====

To use this library you still need knowledge about the ADED contents 
or at least about the entities which you want to communicate. 
You still have to write converter classes from your data to a library 
internal structure following the rules described for the entity in the ADED. 

Library purposes:
- Read/Write ADIS formatted data files
- Receive/Send ISOagriNet formatted streams
- Interface for other programs which need to communicate through ADIS

Supported line types:
D - Definition
V - Values
C - Comments
S - Search
R - Request
T - Termination
Z - EOF

Currently unsupported:
E - End
F - File
I - Include
O - Output

Supported line states:
N - Normal
F - Failure

Unsupported states:
H - Header
S - Sync
D - Delete

FAQ:
===
- ADIS ?
	- ADIS = Agricultural Data Interchange Syntax
- ADED ?
	- ADED = Agricultural Data Element Dictionary
	- An ADED database from the LKV Nordrhein-Westfalen:
		- https://webapp.lkv-nrw.de/AdedDataDictionary/
		
- da fuq? You've got still no clue what's this all about?
	- Either you are wrong here or you just need more information to learn about it...
	More information (in German language!) at http://ian.lkv-nrw.de/index.php?id=308
	Resources are quite rare, I just currently do not know any resource about this subject in English, I'm sorry.
	Only if you want to spend some money to the ISO: http://www.techstreet.com/products/101643
	Or the Book 'ISOagriNET für Entwickler und Entscheider' by Jürgen Goldmann.

License: LGPL.
