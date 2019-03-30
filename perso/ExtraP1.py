#Thiago Bellotti Pavin - Extra - Pratica 1 em Python

#1)Crie uma função sumSquares :: Int -> Int -> Int que calcule a soma dos quadrados de dois números x e y.

def sumSquares(x,y):
    return x**2 + y**2

#2)Crie uma função hasEqHeads :: [Int] -> [Int] -> Bool que verifique se 2 listas possuem o mesmo primeiro elemento. Use a função head e o operador lógico == para verificar igualdade.

def hasEqHeads(lx,ly):
    return lx[0] == ly[0]

#3)Escreva uma função que receba uma lista de nomes e adicione a string "Super " no início de cada nome. Use o operador + para concatenar strings.

def addSuper(s):
    return "Super" + s

def addLSuper(ls):
    return list(map(addSuper, ls))

#4)Crie uma função que receba uma string e retorne o número de espaços nela contidos. Dica: aplique 2 funções consecutivamente.

def espacos(c):
    return c == ' '

def numEspacos(s):
    return len(list(filter(espacos, s)))

#5)Escreva uma função que, dada uma lista de números, calcule 3*n^2 + 2/n + 1 para cada número n da lista. Dica: defina uma função anônima.

def calcula(li):
    return list(map(lambda i: 3*i**2 + 2/i + 1, li))

#6)Escreva uma função que, dada uma lista de números, selecione somente os que forem negativos.

def negativo(li):
    return list(filter(lambda i: i > 0, li))

#7)Escreva uma função que receba uma lista de números e retorne somente os que estiverem entre 1 e 100, inclusive. Dica 1: defina uma função anônima. Dica 2: use o operador 'and' para expressar um 'E' lógico.

def intervalo100(li):
    return list(filter(lambda i: i > 0 and i <= 100, li))

#8)Escreva uma função que, dada uma lista de idades de pessoas no ano atual, retorne uma lista somente com as idades de quem nasceu depois de 1980. Para testar a condição, sua função deverá subtrair a idade do ano atual.

def idade1980(li):
    return list(filter(lambda i: 2019 - i > 1980, li))

#9)Escreva uma função que receba uma lista de números e retorne somente aqueles que forem pares.

def pares(li):
    return list(filter(lambda i: i % 2 == 0, li))

#10)Crie uma função charFound :: Char -> String -> Bool que verifique se o caracter (primeiro argumento) está contido na string (segundo argumento).

def verChar(c,s):
    return bool(list(filter(lambda l: l == c, s)))

#11)Crie uma função que receba uma lista de nomes e retorne outra lista com somente aqueles nomes que terminarem com a letra 'a'.

def terminaComA(ls):
    return list(filter(lambda s: s[-1] == 'a', ls))