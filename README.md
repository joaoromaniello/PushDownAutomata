<h1 align="center">
  #PUSHDOWN AUTOMATA
</h1>

![image](https://user-images.githubusercontent.com/36866624/122079295-a7af2900-cdd3-11eb-989c-63318eeb84c7.png)

Pushdown automatas are used in theories about what can be computed by machines. They are more capable than finite-state machines but less capable than Turing machines. Deterministic pushdown automata can recognize all deterministic context-free languages while nondeterministic ones can recognize all context-free languages, with the former often used in parser design.


# Project Goals

✅ Be able to perform PDA acceptance method conversion  <br />
 --->  ✅ FinalState to EmptyStack <br />
 --->  ✅ EmptyStack to FinalState <br />
✅ Being able to accept or reject any given word <br />
(100% done)

# HOW TO EXECUTE

Inside this project you will find 4 PDA´s inside the "input" folder.
In case you wanna test your own automaton, just insert it using the following JSON design:

{ <br />
  "estados": ["your states here],   <br />
  "alfabeto": "a string with the alphabet characters",  <br />
  "alfabetoPilha" : "a string with all the stack alphabet characters",  <br />
  "simboloInicial" : "stack initial symbol",  <br />
  "regras": [   <br />
    {
      "estadoPartida": "q0",  <br />
      "simbolo": "_", <br />
      "topo": "Z",  <br />
      "estadosDestino": "q0", <br />
      "empilha": "_"  <br />
    },  <br />
    .   <br />
    . <br />
    . <br />
    //Rules <br />
  ],  <br />
  "estadoInicial": "initial state", <br />
  "estadosFinais": [final states] <br />
} <br />

In casr you wanna add an ε transiction, just use the "_" (Underline) character. 


After setting up your automaton, just execute and select it from the initial view.












