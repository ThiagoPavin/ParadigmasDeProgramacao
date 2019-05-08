#Thiago Bellotti Pavin - Extra - Pratica 1 (Haskell) em Prolog

% Eleva um numero ao quadrado (Exemplo da chamada da funcao 'square(2,N).')

square(X,N) :-
    N is X*X.


% Verifica se um numero eh par. A funcao 'mod' retorna resto da divisao inteira (Exemplo da chamada da funcao 'isEven(2).')

isEven(X) :- 
    Y is mod(X,2),
    Y =:= 0.


% Verifica se uma palavra tem mais de 10 caracteres (Exemplo da chamada da funcao 'isLongWork("alo").')

isLongWork(S) :- 
    string_length(S,N),
    N > 10.


% Soma o quadrado dos dois numeros (Exemplo da chamada da funcao 'sumSquares(2,3,N).')

sumSquares(X,Y,N) :-
    N is X**2 + Y**2.


% Adiciona a string "Super" no final de cada palavra da lista (Exemplo da chamada da funcao 'addSuper(["alo","aaaalo","Hey"],L).')

addSuper([],[]).
addSuper(L1,L2) :-
 L1 = [H1|T1],
 L2 = [H2|T2],
 addSuper(T1,T2),
 string_concat(H1,"Super",H2).


% Escreva uma função que, dada uma lista de números, calcule 3*n^2 + 2/n + 1 para cada número n da (Exemplo da chamada da funcao 'calculeFunc([1,2,3],L).')

calculeFunc([],[]).
calculeFunc(L1,L2) :-
    L1 = [H1|T1],
    L2 = [H2|T2],
    calculeFunc(T1,T2),
    H2 is 3*H1**2 + 2 // H1 + 1.


% Escreva uma função que, dada uma lista de números, selecione somente os que forem negativos (Exemplo da chamada da funcao 'somenteNeg([-1,2,3,-2,1,2,-33],L).')

somenteNeg([],[]).
somenteNeg(L1,L2) :- L1 = [H1|T1], L2 = [H2|T2], H1 < 0, somenteNeg(T1,T2), H2 is H1.
somenteNeg(L1,L2) :- L1 = [H1|T1], H1 > 0, somenteNeg(T1,L2).


% Escreva uma função que receba uma lista de números e retorne somente os que estiverem entre 1 e 100.(Exemplo da chamada da funcao 'entre100([-2,2,45,99,100,24,230],L).')

entre100([],[]).
entre100(L1,L2) :- L1 = [H1|T1], L2 = [H2|T2], H1 > 0, H1 < 100, entre100(T1,T2), H2 is H1.
entre100(L1,L2) :- L1 = [H1|T1], H1 >= 100, entre100(T1,L2).
entre100(L1,L2) :- L1 = [H1|T1], H1 =< 0, entre100(T1,L2).
