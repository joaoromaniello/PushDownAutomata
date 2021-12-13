<h1 align="center">
   PUSHDOWN AUTOMATA (PDA)
</h1>

![image](https://user-images.githubusercontent.com/36866624/122079295-a7af2900-cdd3-11eb-989c-63318eeb84c7.png)

Pushdown automatas are used in theories about what can be computed by machines. They are more capable than finite-state machines but less capable than Turing machines. Deterministic pushdown automata can recognize all deterministic context-free languages while nondeterministic ones can recognize all context-free languages, with the former often used in parser design.


# PROJECT GOALS

✅ Be able to perform PDA acceptance method conversion  <br />
 --->  FinalState to EmptyStack <br />
 --->  EmptyStack to FinalState <br />
✅ Be able to accept or reject any given word <br />
(100% done)

# HOW TO EXECUTE

In this project you will find 4 pre made PDA´s inside the "input" folder.
In case you wanna test your own automaton, just insert it using the following JSON design:

```
{
  "estados": ["your states here],  
  "alfabeto": "a string with the alphabet characters", 
  "alfabetoPilha" : "a string with all the stack alphabet characters", 
  "simboloInicial" : "stack initial symbol", 
  "regras": [  
    {
      "estadoPartida": "q0", 
      "simbolo": "_",
      "topo": "Z", 
      "estadosDestino": "q0", 
      "empilha": "_"  
    },  
    .  
    . 
    .
    //Rules 
  ],  
  "estadoInicial": "initial state", 
  "estadosFinais": [final states] 
} 
```

In case you wanna add an ε transiction, just use the "_" (Underscore) character. 


After setting up your automaton, just execute and select it from the initial view.












