# How to Create POST Request Using ADF Copy Activity

## Prerequisites 
* For this tutorial, I'm using JsonPlaceHolder public API, please review it here  https://jsonplaceholder.typicode.com/ 
and read carefully the **PUT**	request.
* We want to update this post https://jsonplaceholder.typicode.com/posts/1 , we are going to change the title into "Fun with ADF" like so : 
 
```
{
  "userId": 1,
  "id": 1,
  "title": "Fun With ADF",
  "body": "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"
} 
```
this is going to be our source **dataset** that we will send to the RestAPI endpoint. 
## Building Pipeline:

1. create a pipeline in ADF like so:


![image](https://user-images.githubusercontent.com/29353746/177719662-be3ec024-d0ab-4d10-b30a-8e4cfd7bcf04.png)

2. In move and transform tab,Drag the Copy Activity in the pipeline:
 
 ![image](https://user-images.githubusercontent.com/29353746/177723298-d2ca2610-35f2-4be3-8448-28bd879c7634.png)
 
3. in Source in copy activity, add the Json that we created above as a dataSet.

![image](https://user-images.githubusercontent.com/29353746/177727313-5c04e2ce-0b27-43f9-a575-998ec4dc73a7.png)

4. in Sink, click on New -> Services and apps -> click and add Rest Activity

![image](https://user-images.githubusercontent.com/29353746/177727752-ea8953e4-ad63-46cb-9b12-3ff31e95c763.png)

5. Click on linkedService -> new and fill the data of the JsonPlaceHolder API like so:

![image](https://user-images.githubusercontent.com/29353746/177728401-23afad89-fa98-4a87-ac05-46087c16505c.png)

Click on Create to create the Rest Activity.
6. In Sink, Configure the Rest request, in our case its a **PUT** request.
![image](https://user-images.githubusercontent.com/29353746/177728157-19c929ac-05b0-4c11-b380-1948d6cbc0e0.png)


7. click on Debug and Validate and publish your piepline. 

## Results:

![image](https://user-images.githubusercontent.com/29353746/177728915-d38fc2e1-f10e-4348-aab1-d0391295402e.png)

  
## License

Copyright © 2022 FIXME

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
