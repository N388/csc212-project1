
public class ContactBST <T extends Comparable<T>> {
public BSTContactNode<T>root,current;
public ContactBST() {
	root=current=null;
}
public boolean empty() {
	return root==null;
}
public boolean full() {
	return false;
}
public T retrive() {
	return current.data;
}
public boolean findkey(String tkey) {
	BSTContactNode<T> p=root,q=root;
	if(empty())
		return false;
	while(p !=null) {
		q=p;
		if(p.key== tkey) {
			current=p;
		return true;
		}
		else if(((Contact)p.data).name.compareTo(tkey)>0)
			p=p.left;
		else
			p=p.right;
	}
	current=q;
	return false;
}
public boolean insertContact(String k,T val) {
	BSTContactNode<T>p,q=current;
	if(findkey(k)) {
		current=q;
		return false;
	}
	p=new BSTContactNode<T>(k,val);
	if(empty()) {
		current=root=p;
	return true;
	}
	else {
		if(q.data.compareTo(p.data)>0) 
			current.left=p;
		
		else 
			current.right=p;
		current=p;
		return true;	
	}
}
public boolean deletContact(String key,LinkedList<Event>events) {
	Boolean removed=new Boolean(false);
	BSTContactNode<T> p;
	p=remove_aux(key,root,removed);
	current=root=p;
	if(!events.empty()) {
	if (events.searchEventName(((Contact)p.data).name)) {
		events.findFirst();
		while (events.current != null) {
			events.deletEvent(((Contact)p.data).name);
			events.findNext();
		}
	}
	}
	return removed.booleanValue();
}
public BSTContactNode<T> remove_aux(String key,BSTContactNode<T> p,Boolean flag){
	BSTContactNode<T> q,child=null;
	if(p==null)
		return null;
	if(((Contact)p.data).name.compareTo(key)>0)
		p.left=remove_aux(key,p.left,flag);
	else if(((Contact)p.data).name.compareTo(key)<0)
		p.right=remove_aux(key,p.right,flag);
	else {
		flag=flag.TRUE;
		if(p.right !=null && p.left != null) {
			q=find_min(p.right);
			p.key=q.key;
			p.data=q.data;
			p.right=remove_aux(q.key,p.right,flag);
		}
		else {
			if(p.right ==null)
				child=p.left;
			else if(p.left == null)
				child=p.right;
			return child;
		}
	}
	return p;
}
public BSTContactNode<T> find_min(BSTContactNode p){
	if(p==null)
		return null;
	while(p.left != null)
		p=p.left;
	return p;
}
public boolean search_nameContact(String p) {
	Boolean flag=new Boolean(false);
	flag=search_nameContact(root,p,flag);
	return flag;
}
private boolean search_nameContact(BSTContactNode<T> p,String q,Boolean flag) {
	if(p==null)
		return false;
	if(((Contact)p.data).name.compareTo(q)==0) {
		current=p;
		flag=true;
		return flag;
	}
	else if(((Contact)p.data).name.compareTo(q)>0)
		return search_nameContact(p.left,q,flag);
	else
		return search_nameContact(p.right,q,flag);
		
}
}

