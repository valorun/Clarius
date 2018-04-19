# **Clarius**
Interface de controle du projet de robotique Clarius. Il s'agit d'une interface graphique réalisée en Java permettant d'établir des connexions SSH avec les raspberries afin de leur faire executer des commandes.

# Pré-requis
- Java 7
- Installation de raspian sur raspberries
- Autorisation de connexion SSH sur les raspberries
- (optionnel)Installation du serveur de reconnaissance vocal "Yana" sur le raspberry destiné aux mouvements du corps(https://github.com/ldleman/yana4all-binaires)
- Python sur le raspberry destiné aux déplacements du chariot

# Installation
Téléchargez et décompressez l'archive de Clarius. Le programme principal et son executable sont à la racine. Les scripts des raspberries sont dans le dossier "Scripts", de même que les exemples des fichiers à modifier.
## Script des raspberries
Chaque raspberry a un dossier correspondant à son nom dans le dossier "Scripts":
- Raspberry buste/vocal
    - Le script "initialize_pins.sh" permet d'initialiser les gpios du raspberry.
    pour le rendre executable, on utilise la commande: ```chmod +x initialize_pins.sh```
au lieu d'éxecuter le script manuellement à chaque fois, nous allons faire en sorte qu'il s'éxecute automatiquement au démarage du raspberry.Pour se faire, on copie le script vers le répertoire "/etc/init.d". On ajoute ensuite le script à la séquence de boot (2 méthodes): 
      1. lancer la commande ```sudo update-rc.d initialize_pins.sh defaults```
      2. ajouter la ligne ```/etc/init.d/initialize_pins.sh || exit 1``` au fichier "/etc/rc.local", avant le "exit 0"
    - (optionnel, si vous ne parvenez pas a vous connecter à votre raspberry à l'aide du programme) Ajoutez la ligne 
    ```
    KexAlgorithms curve25519-sha256@libssh.org,ecdh-sha2-nistp256,ecdh-sha2-nistp384,ecdh-sha2-nistp521,diffie-hellman-group-exchange-sha256,diffie-hellman-group14-sha1,diffie-hellman-group-exchange-sha1,diffie-hellman-group1-sha1
    ```
    dans le fichier "/etc/ssh/sshd_config". Cela permettra à SSH d'accepter plusieurs algorithmes d'échanges de clés (nécessaire pour se connecter avec le programme).
    - (optionnel, si Yana est installé) Ajouter les droits d'execution à l'utilisateur "www-data" pour le fichier "sequenceYana.bash". Plusieurs méthodes existe, la plus simple employée ici consiste à ajouter la ligne 
    ```
    www-data ALL=(ALL) NOPASSWD:/bin/bash /home/pi/Scripts/sequenceYana.bash
    ``` 
    dans le fichier "/etc/sudoers". Ainsi, Yana pourra executer toutes les autres commandes associés aux mouvements (https://github.com/ldleman/yana4all-binaires).
    - Copiez le dossier "Scripts" vers le répertoire personnel (dans notre cas "/home/pi"). Il s'agit de tout les scripts permettant de controller les relais de chaques moteurs.
- Raspberry camera
    - Effectuez les même modifications sur le fichier "/etc/ssh/sshd_config" que pour le raspberry précédent.
- Raspberry vehicule
    - Effectuez les même modifications sur le fichier "/etc/ssh/sshd_config" que pour le raspberry précédent.
    - Copiez le dossier "Scripts" vers "/home/pi". Il contient les scripts permettant de controler les mouvements de la carte de puissance du chariot.
    - Activez l'utilisation du port serie dans le panneau de commande du raspberry (ou via la commande ```sudo raspi-config```).


## Programme principal
Le programme est situé dans le dossier "Clarius" de l'archive. Vous pouvez l'executer via le fichier "exec.bat". Vous pouvez changer les identifiants de connexion, ainsi que les ip des raspberries, ou encore le numéro des pins associé à chaque mouvement dans le fichier "config.txt", généré par défaut au premier démarage du programme (nécessite redémarrage du programme après modifications).
