Optional{

  1. 
    - in metoda takeRounds() se iau randuri prin wait-notify
    - toti playerii sunt sincronizati pe accesul la board. Se verifica daca id-ul curent al playerului este 
    - acelasi cu id-ul pe care il ofera     
    - board-ul. Daca nu, se asteapta egalitatea, iar daca intre timp jocul s-a incheiat se termina threadul
    - daca vine randul playerului, se verifica existenta unui token, si este ales in functie de player un token
    - daca nu mai sunt tokeni, se opreste jocul. Dupa alegere se incrementeaza in Z(numarPlayeri) randul curent 
    - si sunt notificate toate threadurile
    - anuntam de fiecare data cand vedem ca jocul e gata ca poate mai sunt playeri care asteapta si nu stiu ca de
    - fapt e gata
    
  2.
    - in startGame este un runnable timer, daemon, care opreste jocul dupa 10 secunde.
    - cand sunt 3 playeri timerul se seteaza la un minut.
  3. 
    - exista player random, manual si smart
    - cel random alege random un token
    - cel manual afiseaza in consola o lista de tokenuri disponibile, trebuie ales unul
    - cel destept cauta un token sa-si continue progresia. Daca nu gaseste opreste progresia celorlalti, 
    - de la cel mai bun, daca poate, in ordine descrescatoare pana la cel mai prost.
}
