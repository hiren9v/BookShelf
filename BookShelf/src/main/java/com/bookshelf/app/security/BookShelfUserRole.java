package com.bookshelf.app.security;
import java.util.Set;
import java.util.stream.Collectors;
import com.google.common.collect.Sets;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import static com.bookshelf.app.security.BookShelfUserPermission.*;

public enum BookShelfUserRole {
	USER(Sets.newHashSet(BOOK_READ)),
	LIBRARIAN(Sets.newHashSet(BOOK_READ,BOOK_WRITE)),
	ADMIN(Sets.newHashSet(BOOK_READ,BOOK_WRITE,USER_READ,USER_WRITE));
	
	
	private final Set<BookShelfUserPermission> premissions;

	private BookShelfUserRole(Set<BookShelfUserPermission> premissions) {
		this.premissions = premissions;
	}

	public Set<BookShelfUserPermission> getPermissions() {
		return premissions;
	}
	
	public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
	}
	
	
	
	

}
