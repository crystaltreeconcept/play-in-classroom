# Overview

Renders fun tasks page layout
AJAX call to get tasks
on JSON arrival - render the table (form) with submit button
each task has the result encoded together with definition
TBD: in future - encode it with the key so that client side can't decode it


Client -> GET '/FunTasks' -> page with 'loading tasks' -> page with tasks form
Client -> GET '/user/{id}/assignment{id}' ->
Client -> POST '/user/{id}/assignment{id}' -> 'evaluating...' -> results page w/ reset button


TODO: sort out the application.conf
TODO: uncomment SimpleFunPageControllerSpec - tests
TODO: user/assignment passed is tracked in GET -> POST chain
TODO: sort out with CSRF settings and routes

