package tn.esprit.spring.service;

import java.util.List;

import tn.esprit.spring.dao.NotificationRepository;
import tn.esprit.spring.dao.UserDao;
import tn.esprit.spring.dao.formationRepository;
import tn.esprit.spring.entity.Notification;
import tn.esprit.spring.entity.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotifService implements INotifService  {
	
	@Autowired
	formationRepository formationrep;
	
	@Autowired
	UserDao userrep;
	
	@Autowired
	NotificationRepository notifrep;

	@Override
	public void seenNotif(int idNotif) {
		Notification n=notifrep.findById(idNotif).get();
		n.setSeen(1);
		notifrep.save(n);
	}

	@Override
	public List<Notification> getAllNotif() {
		List<Notification> listNotification=(List<Notification>) notifrep.findAll();
		return listNotification;
	}

	@Override
	public void addNotif(Notification notif) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteNotif(int idNotif) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sendNotif(int idFormateur, Notification notif) {
		User a=userrep.findById(idFormateur).orElse(null);
		a.getListNotification().add(notif);
		
		
		
		
	}

	@Override
	public List<Notification> getAllNotSeenNotif(int idFormateur) {
		User f=userrep.findById(idFormateur).orElse(null);
		List<Notification> listNotif=new ArrayList<Notification>();
		listNotif=f.getListNotification();
		List<Notification> listNonSeenNotif=new ArrayList<Notification>();
		for(Notification n:listNotif)
		{
			if(n.getSeen()!=1){
				listNonSeenNotif.add(n);
			}
		}
		
		
		return listNonSeenNotif;

	}

}
