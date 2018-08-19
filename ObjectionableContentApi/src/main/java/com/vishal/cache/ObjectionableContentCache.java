package com.vishal.cache;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("objectionableContentCache")
@Scope(value = "singleton")
public class ObjectionableContentCache {
	
	private Trie trie = new Trie();
	
	@PostConstruct
    public void init() 
    {
    	String keys[] = {"ass", "asshole", "fuck", "fucker", "fuckoff",
				"badass", "whore"};
		for (int i = 0; i < keys.length ; i++)
			trie.insert(keys[i]);
    }

	public Trie getTrie() {
		return trie;
	}

	public void setTrie(Trie trie) {
		this.trie = trie;
	}

	
    
}
