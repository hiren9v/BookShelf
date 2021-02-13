package com.bookshelf.app.security;

public enum BookShelfUserPermission {
	USER_READ("user:read"),
	USER_WRITE("user:write"),
	BOOK_READ("book:read"),
	BOOK_WRITE("book:write"),;
	
	private final String permission;

	BookShelfUserPermission(String permission) {
		this.permission = permission;
	}

	public String getPermission() {
		return permission;
	}
	
}
