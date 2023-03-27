/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package patisseriegestionnaire.jdbc;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import patisseriegestionnaire.interfaces.InterfaceJFrame;
import patisseriegestionnaire.models.Abonne;
import patisseriegestionnaire.models.Client;
import patisseriegestionnaire.models.Commande;
import patisseriegestionnaire.models.CommandePersonnalise;
import patisseriegestionnaire.models.ElementdUnTicket;
import patisseriegestionnaire.models.Evenement;
import patisseriegestionnaire.models.Gateau;
import patisseriegestionnaire.models.Ticket;

/**
 *
 * Classe permettant les interrraction avec la base de données
 * 
 * @author Romane Malherbe
 */
public class PatisserieBD implements InterfaceJFrame{
    
    
  // <editor-fold defaultstate="collapsed" desc="Attributs">
    
    // unique instance possible
    private static PatisserieBD patisserieBD;
    
    // Adresse de la base de données
    private String url = "jdbc:oracle:thin:@iutdoua-ora.univ-lyon1.fr:1521:cdb1";
    // Identifiant de l'utilisateur
    private String user = "p2100657";
    // Mot de passe de l'utilisateur
    private String mdp= "614017";
    // Object qui permet de faire la connection avec la base de données
    private Connection c;
    
   // </editor-fold>  
    
    
  // <editor-fold defaultstate="collapsed" desc="Constructeurs">
    
    /**
     *  Constructeur par default, créer une connection avec la base de données
     * en utilisant les valeurs par default pour l' url,l'utilisateur et le mot de passe
     */
    private PatisserieBD() throws SQLException{
            this.c = DriverManager.getConnection(url, user, mdp);
            System.out.println("DEBUT Connexion");
    }
    
    /**
     * Constructeur qui créer une connection avec la base de données dans l'attribut c,
     * en utilisant les valeurs passé en paramètre.
     * @param url {@link String} Url de la base de donnée
     * @param user {@link String} Nom de l'utilisateur
     * @param mdp  {@link String} Mot de passe de l'utilisateur
     */
    private PatisserieBD(String url,String user,String mdp) throws SQLException{
        
        this.url = url;
        this.user = user;
        this.mdp = mdp;
      
        this.c = DriverManager.getConnection(url, user, mdp);
        System.out.println("DEBUT Connexion");
    }
    // </editor-fold> 
 
    
  // <editor-fold defaultstate="collapsed" desc="Méthodes Static">
    
    /**
     * Méthode static qui renvoie une instance de PatisserieBD, si aucune n'a été créer 
     * la méthode en créé une.
     * 
     * @return Une instance de PatisserieBD
     * @throws SQLException  Renvoie une erreur si la connection c'est mal passé
     */
    public static PatisserieBD getInstance() throws SQLException{
        
        // On vérifie si une instance est déjà créer 
        if (patisserieBD==null){
            
            // si il y en a pas on en crée une
            patisserieBD = new PatisserieBD();
            
        }
        return patisserieBD;
    }
    
    /**
     * Méthode static qui renvoie une instance de PatisserieBD, si aucune n'a été créer 
     * la méthode en créé une avec les paramètres.
     * 
     * @param url {@link String} Url de la base de donnée
     * @param user {@link String} Nom de l'utilisateur
     * @param mdp  {@link String} Mot de passe de l'utilisateur
     * @return Une instance de PatisserieBD
     * @throws SQLException Renvoie une erreur si la connection c'est mal passé
     */
    public static PatisserieBD getInstance(String url,String user,String mdp) throws SQLException{
        
        // On vérifie qu'il n'existe pas déjà une instance avec d'autre paramètre, si c'est le cas on la supprime
        if (patisserieBD!=null && (!patisserieBD.url.equals(url) || !patisserieBD.user.equals(user) || !patisserieBD.mdp.equals(mdp)) ){
            patisserieBD.c.close();
            patisserieBD  = null;
        }
        
        // On vérifie si une instance est déjà créer 
        if (patisserieBD==null) {
            
            // si il y en a pas on en crée une avec les paramètre
            patisserieBD = new PatisserieBD(url,user,mdp);
            
        }
        
        return patisserieBD;
    }
  
  // </editor-fold>
  
    
    /**
     * Méthode qui créer un nouveau client dans la base de données
     * 
     * @param clt {]link Client} qu'on veut créer
     * @return {@link int} niméro du nouveau client dans la base de données
     */
    private int creerClient(Client clt) {
        try {
            
            // création d'un statement
            Statement ordre1 = c.createStatement();
            
            // création d'un resulset contenant la réponse à la requète SQL
            ResultSet r = ordre1.executeQuery("SELECT MAX(numerocli) FROM CLIENT");
            
            // création d'un nouveau numClient à partir du plus grand numéro client contenu dans la base
            r.next();
            int numClient  = r.getInt(1)+1;
            
            // On execute l'ordre SQL qui insert le nouveau client dans la base de données
            ordre1.executeUpdate( "INSERT INTO CLIENT VALUES("+numClient+",'"+clt.getNom()+"','"+clt.getPrenom()+"','"+clt.getMail()+"','"+clt.getTel()+"')");
            
            // fermeture du resultset et du statement
            r.close();
            ordre1.close();
            
            return numClient;
        } catch (SQLException ex) {
            Logger.getLogger(PatisserieBD.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }       
    }
    
  // Méthode hérité de l'interface 'InterfaceJFrame'
    
  // <editor-fold defaultstate="collapsed" desc="Méthodes lier aux gateaux">
    
    /**
     * Méthode permettant de récupérer dans la base de données tout les gateaux/patisserie présente
     * 
     * Les données sont récupérer de la table Gateau et transformer en {@link Gateau} stocké dans une {@link ArrayList}
     * qui est renvoyé.
     * 
     * En cas d'erreur la valeur null est renvoyé.
     * 
     * @return {@link ArrayList} de {@link Gateau} contenant tout les gateaux de la base de données 
     */
    @Override
    public ArrayList<Gateau> getAllGateau(){
        try {
            // Création de la liste qui contiendra les gateaux
            ArrayList<Gateau> l = new ArrayList<>();
            
            // Création d'un Statement
            Statement ordre = c.createStatement();
            
            // Création d'un RésulSet contenant la réponse à la requète SQL
            ResultSet r = ordre.executeQuery("SELECT * FROM gateau");
            
            // Pour chaque ligne de la réponse on instancie un nouveau gateau qu'on ajoute à la liste
            while (r.next()){
                l.add(new Gateau(r.getInt("idGat"),
                r.getString("nomGat"),
                r.getString("descriptionGat"),
                r.getString("description"),
                r.getString("lien_imgGat"),
                r.getFloat("prix"),
                r.getInt("quantite_stocke")
                ));
            }
            
           // Fermeture du resultSet et du Statement
            r.close();
            ordre.close();
            
            // renvoie la liste
            return l;
            
        } catch (SQLException ex) {
            Logger.getLogger(PatisserieBD.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
    
    
    /**
     * Méthode qui ajoute un nouveau gateau à la base de donnée
     * 
     * @param g {@link Gateau} à ajouter à la base de donnée
     * @return {@link boolean} Vrai si l'ajout a eu lieu sans problème sinon renvoie faux  
     */
    @Override
    public boolean addGateau(Gateau g){
        try {
            
            // Création d'un Statement
            Statement ordre1 = c.createStatement();
            
            // Création d'un RésulSet contenant la réponse à la requète SQL
            ResultSet r = ordre1.executeQuery("SELECT MAX(idGat) FROM gateau");
            r.next();
            
            // On récupère le plus grand indice de gateau en base de donné on l'incrémente de 1 et on le met set comme ID du nouveau gateau
            g.setId(r.getInt(1)+1); 
            
            // Fermeture du resulSet
            r.close();
            
            // On execute la requète SQL qui ajoute le nouveau gateau à la base de donnée.
            ordre1.executeUpdate("INSERT INTO gateau(idGat, nomGat, descriptionGat , lien_imgGat , prix	,quantite_stocke ,dateDebut ,dateFin, description) VALUES ("+
                    g.getId()+",'"+g.getNom()+"','"+g.getDescription()+"','"+g.getLienImg()+"',"+g.getPrix()+","+g.getQuantite()+","+g.getDateDebutSQL()+","+g.getDateFinSQL()+",'"+g.getDescriptionGeneral()+"')");
            
            // Fermeture du Statement
            ordre1.close();
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PatisserieBD.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

   
    /**
     *  Méthode qui modifie les informations d'un gateau en base de données
     * 
     * @param g {@link Gateau} qu'on modifie, c'est attribut contienne la ou les nouvelles valeurs
     * @return {@link boolean} Vrai si l'ajout a eu lieu sans problème sinon renvoie faux  
     */
    @Override
    public boolean modifyGateau(Gateau g){
        try {
            
            // Création d'un Statement
            Statement ordre1 = c.createStatement();
            
            // On execute la requète SQL qui modifie le gateau dans la base de donnée.
            ordre1.executeUpdate("UPDATE gateau SET "
                    + "nomGat = '"+g.getNom()+"', descriptionGat= '"+g.getDescription()+"', lien_imgGat='"+g.getLienImg()+"', prix ="+g.getPrix()+""
                            + ",quantite_stocke= "+g.getQuantite()+" ,dateDebut = "+g.getDateDebutSQL()+" ,dateFin="+g.getDateFinSQL()+""
                                    + " WHERE idGat ="+g.getId());
            
            // Fermeture du Statement
            ordre1.close();
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(PatisserieBD.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
  
    /**
     * Méthode qui restocke un gateau, la méthode change la valeur correspondant au nombre de gateau restant en base de données
     * 
     * @param gateau {@link Gateau} pour lequel on vas modifier le stock
     * @param nQuantite {@link int} nouveau stock
     * 
     * @return {@link boolean} Vrai si l'ajout a eu lieu sans problème sinon renvoie faux  
     */
    @Override
    public boolean restockerGateau(Gateau gateau, int nQuantite) {
        try {
            
            // Création d'un Statement
            Statement ordre1 = c.createStatement();
            
            // On execute l'ordre SQL qui modifie la valeur du stock de gateau en base de données.
            ordre1.executeUpdate("UPDATE gateau SET "
                    + "quantite_stocke= "+nQuantite+" WHERE idGat ="+gateau.getId());
            
            // fermeture du Statement
            ordre1.close();
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(PatisserieBD.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    
   /**
    * Méthode qui supprime le gateau passer en paramètre  de la base de donnée
    * 
    * @param gateau {@link Gateau} a supprimer
    * @return {@link boolean} Vrai si l'ajout a eu lieu sans problème sinon renvoie faux 
    */
    @Override
    public boolean removeGateau(Gateau gateau){
        try {
            // Création d'un Statement
            Statement ordre1 = c.createStatement();
            
            // On execute l'ordre SQL qui supprime le gateau de la base de données.
            ordre1.executeUpdate("DELETE FROM gateau WHERE idGat = "+gateau.getId());
            
            // Fermeture du Statement
            ordre1.close();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(PatisserieBD.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
  
  // </editor-fold>
  
    
  // <editor-fold defaultstate="collapsed" desc="Méthodes lier aux Commmandes click and collect">
    
    /**
     * Méthode permettant de récupérer dans la base de données toutes les commandes click and collect à partir du jour actuelle
     * 
     * Les données sont récupérer des la table commandeClickAndConnecte, client ,gateau et gateauCommandee et
     * transformer en {@link Commande} stocké dans une {@link ArrayList}
     * qui est renvoyé.
     * 
     * En cas d'erreur la valeur null est renvoyé.
     * 
     * @return {@link ArrayList} de {@link Commande} contenant tout les commande de la base de données à partir de la date actuelles
     */
    @Override
    public ArrayList<Commande> getAllCommandes(){
        try {
            // Création de la liste qui contiendra les commandes
            ArrayList<Commande> l = new ArrayList<>();
            
            // Création d'un Statement
            Statement ordre = c.createStatement();
            
            // Définition d'un format pour la comparaison de date dans la requete
            DateFormat formater = new SimpleDateFormat("dd-MM-yy");
            
            // Création d'un RésulSet contenant la réponse à la requète SQL
            ResultSet r = ordre.executeQuery("SELECT cac.numeroCom id,g.nomgat nomGateau, g.idGat idg,gc.quantité quantite," +
            "(g.prix * gc.quantité)prix, clt.nomCli nomClt, clt.prenom prenomClt, cac.horaire dtRecup,cac.etat etat, gc.pret p "+
            "FROM commandeClickAndConnecte cac,client clt,gateau g,gateauCommandee gc " +
            "WHERE  SUBSTRING(cac.horaire, 7) = '"+formater.format((new Date()).getTime())+"' AND "
                    + "cac.numeroclient = clt.numerocli AND cac.numeroCom = gc.numcom " +
            "AND gc.idGateau = g.idgat");
            
            // Pour chaque ligne de la réponse on instancie un nouvelle Commande qu'on ajoute à la liste (On fait une commande différente pour chaque type de gateau d'une même commande)
            while (r.next()){
                l.add(new Commande(r.getInt("id"),
                r.getInt("idg"),
                r.getString("nomGateau"),
                r.getInt("quantite"),
                r.getFloat("prix"),
                r.getString("nomClt"),
                r.getString("prenomClt"),
                r.getString("dtRecup"),
                r.getString("etat"),
                Commande.isReady(r.getInt("p"))));
            }
            
            // Fermeture du resultSet et du Statement
            r.close();
            ordre.close();
            
            // renvoie la liste
            return l;
        } catch (SQLException ex) {
            Logger.getLogger(PatisserieBD.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    
    /**
     * Méthode qui valilde la livraison d'une commande de click and collect 
     * 
     * @param commande {@link Commande} qu'on veut valider
     * @return {@link boolean} Vrai si l'ajout a eu lieu sans problème sinon renvoie faux  
     */
    @Override
    public boolean validerCommande(Commande commande) {
        try {
            // Création d'un Statement
            Statement ordre1 = c.createStatement();
            
            // On execute l'ordre SQL qui modifie l'état de la commande en livré
            ordre1.executeUpdate("UPDATE commandeclickandconnecte SET "
                    + "etat= 0 WHERE numerocom ="+commande.getId());
            
            // fermeture du Statement
            ordre1.close();
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(PatisserieBD.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    /**
     * Méthode qui valilde la préparation d'un gateau d'une commande de click and collect 
     * 
     * @param commande {@link Commande} qu'on veut valider
     * @return {@link boolean} Vrai si l'ajout a eu lieu sans problème sinon renvoie faux  
     */
    @Override
    public boolean validerGateauCommande(Commande commande) {
        try {
            // Création d'un Statement
            Statement ordre1 = c.createStatement();
            
            // On execute l'ordre SQL qui modifie l'état de gateaucommande de la base de données.
            ordre1.executeUpdate("UPDATE gateauCommandee SET "
                    + "pret= "+((commande.isReady())? 1:0) +" WHERE numCom ="+commande.getId()+" AND idGateau ="+commande.getGat_id());
            
            // fermeture du Statement
            ordre1.close();
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(PatisserieBD.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    
    
  // </editor-fold>
   
    
  // <editor-fold defaultstate="collapsed" desc="Méthodes lier aux commandes personnalisées">
    
    /**
     * Méthode permettant de récupérer dans la base de données toutes les commandes personnaliser à partir du jour actuelle
     * 
     * Les données sont récupérer des la table client et commandePersonnaliser puis
     * transformer en {@link CommandePersonnalise} stocké dans une {@link ArrayList}
     * qui est renvoyé.
     * 
     * En cas d'erreur la valeur null est renvoyé.
     * 
     * @return {@link ArrayList} de {@link CommandePersonnalise} contenant tout commande personnalisé de la base de données à partir de la date actuelles
     */
    @Override
    public ArrayList<CommandePersonnalise> getAllCommandesPerso(){
        try {
            
            // Création de la liste qui contiendra les commandes peronnalisé
            ArrayList<CommandePersonnalise> l = new ArrayList<CommandePersonnalise>();
            
            // Création d'un Statement
            Statement ordre = c.createStatement();
            
            // Définition d'un format pour la comparaison de date dans la requete
            DateFormat formater = new SimpleDateFormat("yyyy-MM");
        
            
            // Création d'un RésulSet contenant la réponse à la requète SQL
            ResultSet r = ordre.executeQuery("SELECT c.numeroComPerso num, c.forme f,c.taille t, c.gout_creme gc, c.gout_viennoise gv,"
                    +" c.lien_img lm,c.texte_perso txt, c.dtLivraison dt, c.heureLivraison h," +
                    "clt.numeroCli numCli, clt.nomCli nomCli, clt.prenom prenomCli, c.etat e " +
                    "FROM client clt,commandePersonnaliser c WHERE clt.numeroCli = c.numeroClient AND c.dtLivraison >= '"+formater.format((new Date()).getTime())+"'");
            
            // Pour chaque ligne de la réponse on instancie un nouvelle CommandePersonnalise qu'on ajoute à la liste
            while (r.next()){
                l.add(new CommandePersonnalise(r.getInt("num"),
                r.getString("f"),
                r.getString("gv"),
                r.getString("gc"),
                r.getString("t"),
                r.getString("txt"),
                r.getString("lm"),
                r.getDate("dt"),
                r.getString("h"),
                r.getInt("numCli"),
                r.getString("e"),
                r.getString("nomCli"),
                r.getString("prenomCli")  
                ));
            }
            
            // Fermeture du resultSet et du Statement
            r.close();
            ordre.close();
            
            // renvoie la liste
            return l;
            
        } catch (SQLException ex) {
            Logger.getLogger(PatisserieBD.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
  
    
    /**
     * Méthode qui valide la Commande Personnalisé passer paramètre dans la base de donnée
     * @param com {@link CommandePersonnalise} à valider
     * @return {@link boolean} Vrai si l'ajout a eu lieu sans problème sinon renvoie faux  
     */
    @Override
    public boolean validerCommandePerso(CommandePersonnalise com){
       
        try {
            
            // Création d'un Statement
            Statement ordre1 = c.createStatement();
            
            // On execute l'ordre SQL qui modifie la commande personnalise dans la base de donnée.
            ordre1.executeUpdate("UPDATE commandePersonnaliser SET "
                    + "etat = '"+(com.isPret()?"livre":"validee")+ "' WHERE numeroComPerso ="+com.getNum());
            
            // Fermeture du Statement
            ordre1.close();
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(PatisserieBD.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    /**
     * Supprimer une commande personnalisé de la base de données
     * 
     * @param commande {@link CommandePersonnalise} a supprimer
     * @return {@link boolean} Vrai si l'ajout a eu lieu sans problème sinon renvoie faux  
     */
    @Override
    public boolean  removeCommandePerso(CommandePersonnalise commande){
        try {
            
            // Création d'un Statement
            Statement ordre1 = c.createStatement();
            
            // On execute l'ordre SQL qui supprime la commande personnalise de la base de données.
            ordre1.executeUpdate("UPDATE commandePersonnaliser SET "
                    + "etat = 'supprimer' WHERE numeroComPerso ="+commande.getNum());
            
            // Fermeture du Statement
            ordre1.close();
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(PatisserieBD.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    
  // </editor-fold>
    
  // <editor-fold defaultstate="collapsed" desc="Méthodes lier aux ventes au comptoire">
    
    /**
     * Méthode qui ajoute une nouvelle vente au comptoire à la base de donnnées.
     * 
     * @param ticket {@link Ticket} qu'on veut ajouter à la base de données
     * @return {@link boolean} Vrai si l'ajout a eu lieu sans problème sinon renvoie faux  
     */
    @Override
    public boolean ajouterCommandeComptoire(Ticket ticket) {
        try{
            // Création d'un Statement
            Statement ordre1 = c.createStatement();
            
            // Création d'un RésulSet contenant la réponse à la requète SQL
            ResultSet r = ordre1.executeQuery("SELECT MAX(idvente) FROM VENTECOMPTANT");
            r.next();
            
            // Création d'un id pour le nouveau Ticket en fonction des id déjà dans la base de données
            int idVente  = r.getInt(1)+1;
            
            // Fermeture du resultset
            r.close();
            
            // Pour chaque element du ticket
            for (ElementdUnTicket e : ticket){
                
                // On execute l'ordre SQL qui ajoute une vente comptant à la base de données
                ordre1.executeUpdate( "INSERT INTO VENTECOMPTANT VALUES("+idVente+","+e.getGateau().getId()+","+e.getPrix()+","+e.getQuantite()+")"); 
                
                // On execute l'ordre SQL qui modifie le stock de gateau restant dans la base de données.
                ordre1.executeUpdate("UPDATE gateau SET "
                    + "quantite_stocke= quantite_stocke - "+e.getQuantite()+" WHERE idGat ="+e.getGateau().getId());
            }
            
            // Fermeture du Statement
            ordre1.close();
            return true;
            
        }catch (SQLException ex) {
            Logger.getLogger(PatisserieBD.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
  // </editor-fold>
    
  // <editor-fold defaultstate="collapsed" desc="Méthodes lier aux Evenement">    
    
    /**
     * Méthode qui récupére tout les évennement d'un mois 
     * et les stockent dans une {@link ArrayList} qui a 31 emplacement tous initialisé à une valeur null
     * l'emplacement dans la liste correspond à la date de l'évenement dans le mois.
     * Un évenement sans client est un congé
     * 
     * Si il y a une erreur ou qu'il n'y a pas d'événement ce mois la liste aura 31 element 
     * 
     * @param mois {@link int} - numéro du mois (1=Janvier,2=février,..) 
     * @param annee {@link int} - numéro de l'annee 
     * @return {@link ArrayList} de 31 {@link Evenement} 
     */
    @Override
    public ArrayList<Evenement> getListEvenement(int mois, int annee) {
        
        // création de la liste
        ArrayList<Evenement> l = new ArrayList<Evenement>(31);
        
        // On rempli les 31 emplacement avec une valeur null
        for (int i=0; i<32;i++) l.add(null);
        
        try {
            
            // création d'un Statement
            Statement ordre = c.createStatement();
            
            // la variable m représentera le mois dans la requete SQL on ajoute un 0 devant si il n'y a qu'un chiffre "1" -> "01"
            String m = "";
            if (mois<10) m="0";
            m+=mois;
            
        // Récupération des Evenements concernant un Abonnee
        
            // Création d'un RésulSet contenant la réponse à la requète SQL
            ResultSet r = ordre.executeQuery("SELECT DISTINCT e.idevt id,e.dateevt dt,e.description descr,  a.numeroAbo numa " +
            "FROM EVENEMENT e, Abonnee a WHERE e.numeroclient IS NOT NULL  AND e.numeroclient = a.numerocli and"+
            " e.dateevt between '"+annee+"-"+m+"-01' and '"+annee+"-"+m+"-"+Month.of(mois).length(Year.isLeap(annee))+"'");
            
            // pour chaque ligne de la réponse on créer un évenement qu'on place au bonne endroit dans la liste
            while (r.next()){
                
                // création de l'évenement
                Evenement evt = new Evenement(r.getString("descr"),new Abonne(""+(r.getInt("numa"))));
                evt.setDateEvt(r.getDate("dt"));
                
                // ajout de l'évenement dans la liste 
                l.set(evt.getDay()-1, evt);
            }
            
        // Récupération des Evenements concernant un Client
            
            // Affectation au resulset la réponse à la requète SQL
            r = ordre.executeQuery("SELECT e.idevt id,e.dateevt dt,e.description descr, c.nomcli nom, c.prenom p,c.mail m,c.numtelephone tel, c.numeroCli numCli " +
            "FROM EVENEMENT e, Client c WHERE e.numeroclient IS NOT NULL AND e.numeroclient=c.numerocli AND c.numerocli NOT IN (SELECT a.numeroCli FROM ABONNEE a) and"+
            " e.dateevt between '"+annee+"-"+m+"-01' and '"+annee+"-"+m+"-"+Month.of(mois).length(Year.isLeap(annee))+"'");
            
             // pour chaque ligne de la réponse on créer un évenement qu'on place au bonne endroit dans la liste
            while (r.next()){
                
                // création de l'évenement
                Evenement evt = new Evenement(r.getString("descr"),new Client(r.getString("nom"),r.getString("p"),r.getString("m"),r.getString("tel"),r.getInt("numCli")));
                evt.setDateEvt(r.getDate("dt"));
                
                // ajout de l'évenement dans la liste 
                l.set(evt.getDay()-1, evt);
            }
            
            
        // Récupération des dates de congée
        
            // Affectation au resulset la réponse à la requète SQL
            r = ordre.executeQuery("SELECT DISTINCT e.idevt id,e.dateevt dt " +
            "FROM EVENEMENT e WHERE e.numeroclient IS NULL  AND "+
            " e.dateevt between '"+annee+"-"+m+"-01' and '"+annee+"-"+m+"-"+Month.of(mois).length(Year.isLeap(annee))+"'");
            
            // pour chaque ligne de la réponse on créer un évenement qu'on place au bonne endroit dans la liste
            while (r.next()){
                
                // création de l'évenement 
                Evenement evt = new Evenement();
                evt.setDateEvt(r.getDate("dt"));
                
                // ajout de l'évenement dans la liste 
                l.set(evt.getDay()-1, evt);
            }
            
            // fermeture du resultSet et du Statement 
            r.close();
            ordre.close();
            
            // renvoie de la liste
            return l;
            
        } catch (SQLException ex) {
            Logger.getLogger(PatisserieBD.class.getName()).log(Level.SEVERE, null, ex);
            return l;
        }
    }

    /**
     * Méthode qui ajoute un nouvelle evennement à la base de données
     * 
     * @param e {@link Evenement} qu'on veut ajouter à la base de données
     * 
     * @return {@link boolean} Vrai si l'ajout a eu lieu sans problème sinon renvoie faux  
     */
    @Override
    public boolean ajoutEvenement(Evenement e) {
        try {
            
            // Création d'un Statement
            Statement ordre1 = c.createStatement();
            
            // Création d'un RésulSet contenant la réponse à la requète SQL
            ResultSet r = ordre1.executeQuery("SELECT MAX(idevt) FROM EVENEMENT");
            r.next();
            // Création d'un id pour le nouvel evenement en fonction des id déjà dans la base de données
            int idEvt  = r.getInt(1)+1;
            
            // Si l'evennement est un congé
            if (e.isConge()){
                
                // On execute l'ordre SQL qui ajoute un evenement de type conge à la base de données
                ordre1.executeUpdate( "INSERT INTO EVENEMENT VALUES("+idEvt+",'"+e.getDate()+"',NULL,NULL)"); 
                
            }else{
                
                int numClient;
                
                // On regarde si le client de l'evennement est un abonnée
                if (e.getClient() instanceof Abonne) {
                    
                    // si oui on récupère le numéro client grâce au numéro abonnée
                    r = ordre1.executeQuery("SELECT numeroCli FROM ABONNEE WHERE numeroAbo = "+((Abonne)e.getClient()).getNumAbo());
                    r.next();
                    numClient = r.getInt(1);
                    
                } else {
                    
                    // sinon on creer un nouveau client (la méthode renvoie le nouveau numéro client)
                    numClient = this.creerClient(e.getClient());
                    
                } 
                
                // On execute l'ordre SQL qui ajoute un evenement à la base de données
                ordre1.executeUpdate( "INSERT INTO EVENEMENT VALUES("+idEvt+",'"+e.getDate()+"','"+e.getDescr()+"',"+numClient+")");
            
            }
            // fermeture du resultset et du statement 
            r.close();
            ordre1.close();
            
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(PatisserieBD.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    /**
     * Méthode qui supprime un évenement de la base de données
     * 
     * @param e {@link Evenement} qu'on veut supprimer
     * @return {@link boolean} Vrai si l'ajout a eu lieu sans problème sinon renvoie faux  
     */
    @Override
    public boolean supprimerEvenement(Evenement e) {
        try {
            // création de la liste
            Statement ordre1 = c.createStatement();
            
            // On execute l'ordre SQL qui supprime l'evenement de la base de données
            ordre1.executeUpdate("DELETE FROM evenement WHERE dateevt = '"+e.getDate()+"'");
            
            // fermeture du statement
            ordre1.close();
            
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(PatisserieBD.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    
    }

    /**
     * Méthode qui modifie les valeur d"un évenement présent en base de donnée
     * 
     * @param e {@link Evenement} qu'on veut supprimer
     * @return {@link boolean} Vrai si l'ajout a eu lieu sans problème sinon renvoie faux 
     */
    @Override
    public boolean ajoutModificationEvmt(Evenement e) {
        try {
            // création d'un statement
            Statement ordre1 = c.createStatement();
            
            // si l'évenement est maintenant un congé
            if (e.isConge()){
                
                // On execute l'ordre SQL qui modifie l'evenement de la base de données
                ordre1.executeUpdate("UPDATE Evenement SET "
                        + "description = NULL, numeroclient=NULL"+
                        " WHERE dateevt = '"+e.getDate()+"'");
                
                // fermeture du statement
                ordre1.close();
                
                return true;
            }
            else{
                
                // création d'un resultset
                ResultSet r;
                int numClient;
                
                // si le client est un Abonné
                if (e.getClient() instanceof Abonne){
                    
                    // onrecupère le numéro de client à partir du numéro abonnée
                    r = ordre1.executeQuery("SELECT numeroClient FROM ABONNEE WHERE numeroAbo = "+((Abonne)e.getClient()).getNumAbo());
                    r.next();
                    numClient = r.getInt(1);
                    
                    // On execute l'ordre SQL qui modifie l'evenement dans la base de données
                    ordre1.executeUpdate("UPDATE Evenement SET "
                        + "description = '"+e.getDescr()+"', numeroclient="+ numClient +
                            " WHERE dateevt = '"+e.getDate()+"'");
                    
                  // sinon
                }else{
                    
                    // on récupère l'ancien numéro client connecter àà l'évenement
                    r = ordre1.executeQuery("SELECT numeroClient FROM evenement e WHERE dateevt = '"+e.getDate()+"'");
                    if (r.next())
                        numClient = r.getInt(1);
                    else { // si il n'y en as pas on créer un nouveau client
                        numClient = this.creerClient(e.getClient());
                    }
                    
                    // On execute l'ordre SQL qui modifie l'evenement dans la base de données 
                    ordre1.executeUpdate("UPDATE Evenement SET "
                            + "description = '"+e.getDescr()
                            +"', numeroclient="+ numClient +
                            " WHERE dateevt = '"+e.getDate()+"'");
                    
                    //On execute l'ordre SQL qui modifie le Client associé ç l'évenement dans la base de données
                    Client c = e.getClient();
                    ordre1.executeUpdate("UPDATE Client SET  nomcli = '"+c.getNom()
                            +"', prenom = '"+c.getPrenom()+"',mail ='"+c.getMail()+"',numtelephone='"+c.getTel()+
                            "' WHERE numerocli = "+numClient);
                    
                }
                
                // fermeture du resultset et du statement
                r.close();
                ordre1.close();
                
                return true;
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(PatisserieBD.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

  // </editor-fold>
    
  /**
     * Méthode qui ferme la connexion et qui ferme aussi l'appli
     */
    @Override
    public void fermeture() {
    try {
            this.c.close();
            System.out.println("Fermeture Connexion");
            System.exit(0);
        } catch (SQLException ex) {
            Logger.getLogger(PatisserieBD.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    } 

    
    

  

    
    
}
