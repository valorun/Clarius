## Clarius
Interface de controle du projet de robotique Clarius. Il s'agit d'une interface graphique réalisée en Java permettant d'établir des connexions SSH avec les raspberries afin de leur faire executer des commandes.

## Pré-requis
- Java 7
- Installation des raspberries
- Autorisation de connexion SSH sur les raspberries

## Installation
Téléchargez et décompressez l'archive de Clarius. Le programme principal et son executable sont à la racine. Les scripts des raspberries sont dans le dossier "Scripts".
# Script des raspberries
Chaque raspberry a un dossier correspondant à son nom dans le dossier "Scripts":
- raspberry buste/vocal
    - le script initialize_pins.sh permet d'initialiser les gpios du raspberry.
    pour le rendre executable, on utilise la commande: chmod +x initialize_pins.sh
au lieu d'éxecuter le script manuellement à chaque fois, nous allons faire en sorte qu'il s'éxecute automatiquement au démarage du raspberry.
pour se faire, on copie le script vers le répertoire /etc/init.d
on ajoute ensuite le script à la séquence de boot (2 méthodes): 
      1. lancer la commande "sudo update-rc.d initialize_pins.sh defaults"
      2. ajouter la ligne "/etc/init.d/initialize_pins.sh || exit 1" au fichier /etc/rc.local, avant le "exit 0"



# Programme principal
Le programme étant Executez le fichier "exec.bat" pour executer le programme. Vous pouvez changer les identifiants de connexion, ainsi que les ip des raspberries, ou encore le numéro des pins associé à chaque mouvement dans le fichier "config.txt" (nécessite redémarrage du programme après modifications).
