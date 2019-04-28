%1)Defina um predicado odd(N) que seja verdadeiro se N for um número ímpar.

odd(N) :-
 D is mod(N,2),
 D =:= 1.

%2)Defina um predicado recursivo hasN(L,N) que seja verdadeiro se L for uma lista de N elementos.

hasN([],0).
hasN(L,N) :-
 L = [H|T],
 hasN(T,A),
 N is A + 1.
 
%3)Defina um predicado recursivo inc99(L1,L2), de forma que L2 seja uma lista com todos os elementos de L1 acrescidos da constante 99.

inc99([],[]).
inc99(L1,L2) :- 
 L1 = [H1|T1],
 L2 = [H2|T2],
 inc99(T1,T2),
 H2 is H1+99.

%4)Defina um predicado recursivo incN(L1,L2,N), de forma que L2 seja uma lista com todos os elementos de L1 acrescidos da constante N.

incN([],[],_).
incN(L1,L2,N):-
 L1 = [H1|T1],
 L2 = [H2|T2],
 incN(T1,T2,N),
 H2 is H1+N.

%5)Defina um predicado recursivo comment(L1,L2), de forma que L1 seja uma lista de strings e L2 tenha todos os elementos de L1 concatenados com o prefixo "%%".Dica: consulte predicados Prolog para manipulação de strings.

comment([],[]).
comment(L1,L2) :-
 L1 = [H1|T1],
 L2 = [H2|T2],
 comment(T1,T2),
 string_concat("%%",H1,H2).

%6)Defina um predicado recursivo onlyEven(L1,L2), de forma que L2 seja uma lista só com os elementos pares de L1.

onlyEven([],[]).
onlyEven(L1,L2) :- L1 = [H1|T1] , L2 = [H2|T2] , A is mod(H1,2) , A =:= 0, onlyEven(T1,T2) , H2 is H1.
onlyEven(L1,L2) :- L1 = [H1|T1] , A is mod(H1,2) , A =:= 1, onlyEven(T1,L2).

%7)Defina um predicado recursivo countdown(N,L), de forma que L seja uma lista com os números [N, N-1, N-2, .., 1], sendo N um número positivo.

countdown(0,[]).
countdown(N,L) :- L = [H1|T1],A is N-1, countdown(A,T1), H1 is N.

%8)Defina um predicado recursivo nRandoms(N,L), de forma que L seja uma lista com N números gerados aleatoriamente.

nRandoms(0,[]).
nRandoms(N,L) :- L = [H1|T1] , random_between(0,100,X) , H1 is X, AUX is N-1 , nRandoms(AUX,T1).

%9)Defina um predicado recursivo potN0(N,L), de forma que L seja uma lista de potências de 2, com expoentes de N a 0. 

potN0(0,[1]).
potN0(N,L) :- L = [H1|T1] , H1 is 2**N , AUX is N-1 , potN0(AUX,T1).

%10)Defina um predicado recursivo zipmult(L1,L2,L3), de forma que cada elemento da lista L3 seja o produto dos elementos de L1 e L2 na mesma posição do elemento de L3.

zipmult([],[],[]).
zipmult(L1,L2,L3) :- L1 = [H1|T1] , L2 = [H2|T2] , L3 = [H3|T3] , H3 is H1*H2 , zipmult(T1,T2,T3).

%11)Defina um predicado recursivo potencias(N,L), de forma que L seja uma lista com as N primeiras potências de 2, sendo a primeira 2^0 e assim por diante.

potencias(N,L) :- potN0(N,Laux), reverse(Laux, L).

%12)Defina um predicao recursivo cedulas(V,L1,L2), que receba um valor V e uma lista L1 de cédulascom valores em Reais(R$), em ordem decrescente, e obtenha a lista L2 decompondo o valor V em 0 ou mais cédulas de cada tipo.

cedulas(0,[],[]).
cedulas(V,L1,L2) :- L1 = [H1|T1] , L2 = [H2|T2] , V < H1, H2 is 0 , cedulas(V,T1,T2).
cedulas(V,L1,L2) :- L1 = [H1|T1] , L2 = [H2|T2] , H2 is V div H1 , AUX is V mod H1 , cedulas(AUX,T1,T2).
