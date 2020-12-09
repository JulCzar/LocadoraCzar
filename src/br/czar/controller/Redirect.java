package br.czar.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.czar.util.Utils;

@Named
@RequestScoped
public class Redirect {
	public void to(String page) {
		Utils.redirect(page);
	}
}
