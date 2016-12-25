Clasa Test instantiaza un nou Bash.
In Bash un obiect de tip InputReader citeste linie cu linie si intoarce un vector String[] args. Vectorul este trimis ca parametru catre CommandFactory care intoarce comanda specifica.
CommandFactory are o librarie de comenzi, retinuta sub forma unui hash ce are cheia numele comenzii, iar valoarea referinta catre clasa comenzii.

Fiecare comanda mosteneste clasa abstracta de baza Command, unde sunt retinuti si parametrii ei.
