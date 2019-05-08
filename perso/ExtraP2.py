#Thiago Bellotti Pavin - Extra - Pratica 2 em Python

#1)Crie uma função isVowel :: Char -> Bool que verifique se um caracter é uma vogal ou não.

def isVowel(c):
    if c.lower() == 'a':
        return True
    elif c.lower() == 'e':
        return True
    elif c.lower() == 'i':
        return True
    elif c.lower() == 'o':
        return True
    elif c.lower() == 'u':
        return True
    else:
        return False


#2)Escreva uma função addComma, que adicione uma vírgula no final de cada string contida numa lista.

def addComma(ls):
    return list(map(lambda s: s + ',', ls))


#3)Crie uma função htmlListItems :: [String] -> [String], que receba uma lista de strings e retorne outra lista
#contendo as strings formatadas como itens de lista em HTML. Resolva este exercício COM e SEM funções anônimas (lambda).

#3.1)Sem função anonima

def addStr(s):
    return "<LI>" + s + "</LI>"

def htmlListItems1(ls):
    return list(map(addStr, ls))

#3.2)Com função anonima

def htmlListItems2(ls):
    return list(map(lambda s: "<LI>" + s + "</LI>", ls))


#4)Defina uma função que receba uma string e produza outra retirando as vogais, conforme os exemplos abaixo.
#Resolva este exercício COM e SEM funções anônimas (lambda).

#4.1)Sem função anonima

def isNotVowel(c):
    if c.lower() == 'a':
        return False
    elif c.lower() == 'e':
        return False
    elif c.lower() == 'i':
        return False
    elif c.lower() == 'o':
        return False
    elif c.lower() == 'u':
        return False
    else:
        return True

def removeVogais1(s):
    return "".join(filter(isNotVowel, s))

#4.2)Com função anonima

def removeVogais2(s):
    return "".join(filter(lambda c: False if c.lower() == 'a' or c.lower() == 'e' or c.lower() == 'i' or c.lower() == 'o' or c.lower() == 'u' else True, s))


#7)Escreva uma função isInt :: String -> Bool que verifique se uma dada string só contém dígitos de 0 a 9.

def isInt(s):
    return s.isdigit()
