package dev.sandrocaseiro.springbootitExample.steps;

import io.cucumber.java8.Pt;

public class AutenticacaoSteps extends BaseSteps implements Pt {
    public AutenticacaoSteps() {
        Dado("que estou autenticado", () -> {
            state.setAuth("teste");
        });
    }
}
