# Path-Finder
<h2>Overview</h2>
A Java program that lets the user create their own maze, mark the start and end points, and watch an algorithm search it's way through.  This was an independant project that was completed over the winter break of 2018 when I was learning about Java and it's capabilities. Below is a demonstration of a custom maze after hitting the "search" button.
<br><br>

<p align="center">
<img src="Searching%20Algorithm/demo.gif" width="600px">
</p>

<h2>Using The Program</h2>

When you first launch the program you are greeted with an empty canvas, On the left side of the program you can see eight interactable buttons, Draw Barriers, Erase Barriers, Mark Start, Mark End, Search, Restart, Save Board, and Load Board. By default, Draw Barriers is selected. This is indicated by a green circle in the upper left corner of the Draw Barriers button.

<img src="Searching%20Algorithm/ScreenShots/Screen1.png" width="800px">

<h2>Drawing a Maze</h2>
You can drag the mouse across the squares to map out your very own maze, the only rules is that there needs to be a minimum of 1 square drawn in a square around the entire maze.  This is to ensure that the algorithm only search inside of the maze and does not continue to seek outside of what is necessary. 

<img src="Searching%20Algorithm/ScreenShots/Screen2.png" width="800px">

<h2>Searching Through the Maze</h2>
After drawing your maze, marking the start and end positions, you can press the search button, the algorithm will essentialy search down all the paths until it finds the end.  The blue squares are where one branch of the algorithm is currently searching for the red square.
<img src="Searching%20Algorithm/ScreenShots/Screen3New.png" width="800px">

<h2>Finding the Most Efficient Path</h2>
Once the end square is found, the program will automatically map out the most efficent path that it took in order to reach the end in blue. You can click the Restart button on the left panel in order to start again, or save you maze to be loaded again in the future. The mazes save in the same directory in which the program is run.

<img src="Searching%20Algorithm/ScreenShots/Screen4.png" width="800px">
