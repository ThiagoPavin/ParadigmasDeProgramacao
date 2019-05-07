%-------%
% FATOS %
%-------%

% Possiveis 3 motivos pelo crime

% Dinheiro

pobre(bia).
pobre(pedro).
pobre(maria).

% Ciumes

ciume(caren).
ciume(alice).

% Insanidade

insanidade(adriano).
insanidade(maria).

% Locais onde as pessoas estavam e seu respectivo dia

% Pedro
local(pedro,sm,segunda).
local(pedro,sm,terca).
local(pedro,poa,quarta).
local(pedro,sm,quinta).
local(pedro,apt,sexta).

% Caren
local(caren,poa,segunda).
local(caren,poa,terca).
local(caren,poa,quarta).
local(caren,sm,quinta).
local(caren,apt,sexta).

% Henrique
local(henrique,apt,segunda).
local(henrique,poa,terca).
local(henrique,apt,quarta).
local(henrique,apt,quinta).
local(henrique,apt,sexta).

% Bia
local(bia,apt,segunda).
local(bia,poa,terca).
local(bia,poa,quarta).
local(bia,sm,quinta).
local(bia,apt,sexta).

% Adriano
local(adriano,apt,segunda).
local(adriano,apt,terca).
local(adriano,sm,quarta).
local(adriano,apt,quinta).
local(adriano,apt,sexta).

% Alice
local(alice,apt,segunda).
local(alice,poa,terca).
local(alice,poa,quarta).
local(alice,apt,quinta).
local(alice,apt,sexta).

% Bernardo
local(bernardo,sm,segunda).
local(bernardo,sm,terca).
local(bernardo,poa,quarta).
local(bernardo,sm,quinta).
local(bernardo,apt,sexta).

% Maria
local(maria,apt,segunda).
local(maria,sm,terca).
local(maria,sm,quarta).
local(maria,sm,quinta).
local(maria,apt,sexta).

%--------%
% REGRAS %
%--------%

assassino(X) :- motivos(X), acesso(X).

% Existem tres possiveis motivos para o crime: dinheiro, ciume ou insanidade

motivos(X) :- ciume(X); insanidade(X); pobre(X) .

% Conferir todas as informacoes de dia e local

acesso(X) :- conferir_chave(X), arma(X).

% Conferir as pessoas que podem ter a chave pelo dia e local

conferir_chave(X) :- local(X,sm,segunda); local(X,poa,terca).

% Conferir as pessoas que podem ter a arma do crime pelo dia e local

arma(X) :- conferir_arma_baseball(X); conferir_arma_martelo(X).

conferir_arma_baseball(X) :- local(X,poa,quinta); local(X,sm,quarta).

conferir_arma_martelo(X) :- local(X,apt,quarta); local(X,apt,quinta).
