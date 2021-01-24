
### System overall

One Database, four functions

1 . Recommend System

- On the access page, viewer should choose his favor. 
- System read a total data of poem and picks some of that which is matched with favors.

2 . Listing

- Do List contents with title and Author and date written.
- Pick contents based on condition set when first page.
- Page has a limit of the number of contents and if the number of picked contents exceeds the limit, then system rolls a dice for choosing list randomly. Viewer has to click the refresh button to change the list.
- Click cotent, then access into a poem body screen. 


3 . Filtering

- Name, Author, date Filtering
- Async? No, It is happened after filter click.


4 . Starring

- Viewer express his love to poem by starring.
- This action is allowed on poem body screen.

### Database structure

Three database

1 . favorDB

|cid|genre|form|language|
|---|---|---|---|
|---|---|---|---|

2 . titleDB

|cid|title|author|date|
|---|---|---|---|
|---|---|---|---|

3 . bodyDB

|cid|body|
|---|---|
|---|---|

4 . starDB

|cid|star|
|---|---|
|---|---|


### Required Operation

- On front viwer Input favor -> SQL query -> get list of cid -> get list of content
- Construct a temporary repository containing all of the picked list. 
- Choose only ten or more contents randomly, and send its information to front
- If viewer clicks a refresh button, request make a replay of the forward stiuation.
- Filtering process don't depend back-end, it begins and ends on front, in the other words all is done by javascript. 
- Starring is made between front and object repository, viewer's starring update data located on memory not database, the process for updating database progress by other thread.
