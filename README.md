# **Clarius**
Interface de controle du projet de robotique Clarius. Il s'agit d'une interface graphique réalisée en Java permettant d'établir des connexions SSH avec les raspberries afin de leur faire executer des commandes.

# Pré-requis
- Java 7
- Installation de raspian sur raspberries
- Autorisation de connexion SSH sur les raspberries
- (optionnel)Installation du serveur de reconnaissance vocal "Yana" sur le raspberry destiné aux mouvements du corps(https://github.com/ldleman/yana4all-binaires)
- Python sur le raspberry destiné aux déplacements du chariot

# Installation
Téléchargez et décompressez l'archive de Clarius. Le programme principal et son executable sont à la racine. Les scripts des raspberries sont dans le dossier "Scripts".
## Script des raspberries
Chaque raspberry a un dossier correspondant à son nom dans le dossier "Scripts":
- Raspberry buste/vocal
    - Le script "initialize_pins.sh" permet d'initialiser les gpios du raspberry.
    pour le rendre executable, on utilise la commande: chmod +x initialize_pins.sh
au lieu d'éxecuter le script manuellement à chaque fois, nous allons faire en sorte qu'il s'éxecute automatiquement au démarage du raspberry.Pour se faire, on copie le script vers le répertoire "/etc/init.d". On ajoute ensuite le script à la séquence de boot (2 méthodes): 
      1. lancer la commande "sudo update-rc.d initialize_pins.sh defaults"
      2. ajouter la ligne "/etc/init.d/initialize_pins.sh || exit 1" au fichier /etc/rc.local, avant le "exit 0"
    - Copiez fichier "sshd_config" vers dans "/etc/ssh/" et remplacer celui existant. Ce fichier a été modifier pour accepter plusieurs algorithmes d'échanges de clés (nécessaire pour se connecter avec le programme).
    - (optionnel, si Yana est installé)Copiez le fichier "sudoers" vers dans "/etc". Les lignes ajoutées dans ce fichier vont permettre de donner le droit d'éxecuter les autres scripts au programme "Yana" (https://github.com/ldleman/yana4all-binaires).
    - Copiez le dossier "Scripts" vers le répertoire personnel (dans notre cas "/home/pi"). Il s'agit de tout les scripts permettant de controller les relais de chaques moteurs.
- Raspberry camera
    - Copiez les fichiers "sshd_config" et "sudoers" au même endroit que pour le raspberry précédent.
- Raspberry vehicule
    - Copiez les fichiers "sshd_config" et "sudoers" au même endroit que pour le raspberry précédent.
    - Copiez le dossier "Scripts" vers "/home/pi". Il contient les scripts permettant de controler les mouvements de la carte de puissance du chariot.
    - Activez l'utilisation dans le panneau de commande du raspberry (ou via la commande "sudo raspi-config").


## Programme principal
Le programme étant Executez le fichier "exec.bat" pour executer le programme. Vous pouvez changer les identifiants de connexion, ainsi que les ip des raspberries, ou encore le numéro des pins associé à chaque mouvement dans le fichier "config.txt" (nécessite redémarrage du programme après modifications).
