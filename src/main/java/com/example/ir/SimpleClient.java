package com.example.ir;

import com.example.ir.clients.Activator;
import com.example.ir.clients.Client;

@Client
interface SimpleClient {

	@Activator
	void activate();
}
