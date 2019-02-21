# CareDog

This is a game that is about 4 years old that I found on my hard drive. Thought I would share it for fun. Just click the jar file to run it. Have fun!

## UML diagrams

Basic Idea on how the classes communicate with one another

```mermaid
sequenceDiagram
NPC -->> GameObjects: instaniate and pass objects
Items -->> GameObjects: instaniate and pass objects
GameObjects -> ColorPanel: rendering active states
GameObjects <-> Event Handlers: passing states
Text -x CareDog: instaniate font
Text -x CareDog: instaniate game

```

## Screen Shots

![enter image description here](https://i.imgur.com/CCxKJu5.gif)

## Note

Since I made this game a while back. I am aware that if you try to recompile it must be done in a certain order. You also have to make certain changes. You must uncouple Items and NPC from GameObjects. Then compile GameObjects than the event handlers, (Panel Listener, Event Listener, etc...) Then Compile ColorPanel and recompile the rest of the project.
