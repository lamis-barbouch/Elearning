package tn.esprit.spring.service;

import java.util.List;
import tn.esprit.spring.entity.Formation;
import tn.esprit.spring.entity.User;
import tn.esprit.spring.entity.Notification;


public interface INotifService {
	
	public void seenNotif(int idNotif);
	public List<Notification> getAllNotif();
	public List<Notification> getAllNotSeenNotif(int idFormateur);
	public void addNotif(Notification notif);
	public void deleteNotif(int idNotif) ;
	public void sendNotif(int idFormateur,Notification notif);
	
}
