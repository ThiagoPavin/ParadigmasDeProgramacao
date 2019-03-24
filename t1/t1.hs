import Data.Char

--1)Crie uma fun��o isVowel :: Char -> Bool que verifique se um caracter � uma vogal ou n�o.

isVowel :: Char -> Bool
isVowel c = if (c == 'a' || c == 'e'|| c == 'i'|| c == 'o'|| c == 'u' || c == 'A' || c == 'E'|| c == 'I'|| c == 'O'|| c == 'U') then True else False


--2)Escreva uma fun��o addComma, que adicione uma v�rgula no final de cada string contida numa lista.

addComma :: [String] -> [String]
addComma l = map (++ ",") l


--3)Crie uma fun��o htmlListItems :: [String] -> [String], que receba uma lista de strings e retorne outra lista contendo as strings formatadas como itens de lista em HTML. Resolva este exerc�cio COM e SEM fun��es an�nimas (lambda).

--Sem fun��o anonima

addStr :: String -> String
addStr s = "<LI>" ++ s ++ "</LI>"

htmlListItems1 :: [String] -> [String]
htmlListItems1 ls = map (addStr) ls

--Com fun��o anonima

htmlListItems2 :: [String] -> [String]
htmlListItems2 ls = map (\s -> "<LI>" ++ s ++ "</LI>") ls


--4)Defina uma fun��o que receba uma string e produza outra retirando as vogais, conforme os exemplos abaixo. Resolva este exerc�cio COM e SEM fun��es an�nimas (lambda).

--Sem fun��o anonima

isNotVowel :: Char -> Bool
isNotVowel c = c `notElem` "aeiouAEIOU"

removeVogais1 :: String -> String
removeVogais1 s = filter (isNotVowel) s

--Com fun��o anonima

removeVogais2 :: String -> String
removeVogais2 s = filter (\c -> c `notElem` "aeiouAEIOU") s

--5)Defina uma fun��o que receba uma string, possivelmente contendo espa�os, e que retorne outra string substituindo os demais caracteres por '-',mas mantendo os espa�os. Resolva este exerc�cio COM e SEM fun��es an�nimas (lambda). 

--Sem fun��o anonima

trocaChar :: Char -> Char
trocaChar c = if c == ' ' then ' ' else '-'

codifica1 :: String -> String
codifica1 s = map(trocaChar)s

--Com fun��o anonima

codifica2 :: String -> String
codifica2 s = map(\c -> if c == ' ' then ' ' else '-')s


--6)Escreva uma fun��o firstName :: String -> String que, dado o nome completo de uma pessoa,obtenha seu primeiro nome. Suponha que cada parte do nome seja separada por um espa�o e que n�o existam espa�os no in�cio ou fim do nome.Dica: estude fun��es pr�-definidas em Haskell (List operations -> Sublists) em http://hackage.haskell.org/package/base-4.10.1.0/docs/Prelude.html#g:18. Exemplos de uso da fun��o:

firstName :: String -> String
firstName s = takeWhile (/= ' ')s


--7)Escreva uma fun��o isInt :: String -> Bool que verifique se uma dada string s� cont�m d�gitos de 0 a 9. 

isInt :: String -> Bool
isInt s = if length(filter(`notElem` "0123456789")s) == 0 then True else False


--8)Escreva uma fun��o lastName :: String -> String que, dado o nome completo de uma pessoa, obtenha seu �ltimo sobrenome. Suponha que cada parte do nome seja separada por um espa�o e que n�o existam espa�os no in�cio ou fim do nome.

lastName :: String -> String
lastName s = reverse(takeWhile (/= ' ') (reverse s))


--9)Escreva uma fun��o userName :: String -> String que, dado o nome completo de uma pessoa, crie um nome de usu�rio (login) da pessoa, formado por: primeira letra do nome seguida do sobrenome, tudo em min�sculas. Dica: estude as fun��es pr�-definidas no m�dulo Data.Char, para manipula��o de mai�sculas e min�sculas. Voc� precisar� carregar este m�dulo usando --import Data.Char no interpretador ou no in�cio do arquivo do programa.

userName :: String -> String
userName s = map toLower ((take 1 s) ++ (lastName s))


--10)Escreva uma fun��o encodeName :: String -> String que substitua vogais em uma string, conforme o esquema a seguir: a = 4, e = 3, i = 2, o = 1, u = 0.

encodeName :: String -> String
encodeName s = map (\c -> if c == 'a' then '4' else if c == 'e' then '3' else if c == 'i' then '2' else if c == 'o' then '1' else if c == 'u' then '0' else c) s


--11)Escreva uma fun��o betterEncodeName :: String -> String que substitua vogais em uma string, conforme este esquema: a = 4, e = 3, i = 1, o = 0, u = 00.

betterEncodeName :: String -> String
betterEncodeName s = concatMap (\c -> if c == 'a' then "4" else if c == 'e' then "3" else if c == 'i' then "1" else if c == 'o' then "0" else if c == 'u' then "00" else [c] ) s


--12)Dada uma lista de strings, produzir outra lista com strings de 10 caracteres, usando o seguinte esquema: strings de entrada com mais de 10 caracteres s�o truncadas, strings com at� 10 caracteres s�o completadas com '.' at� ficarem com 10 caracteres.

auxFunc10 :: String -> String
auxFunc10 s = if length s > 10 then take 10 s else if length s < 10 then auxFunc10 (s ++ ".") else s

func10 :: [String] -> [String]
func10 ls = map auxFunc10 ls
