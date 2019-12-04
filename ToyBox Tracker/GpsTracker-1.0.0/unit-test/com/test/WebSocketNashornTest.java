/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import jdk.nashorn.api.scripting.NashornScriptEngineFactory;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Pussy Destroyer
 */
public class WebSocketNashornTest {
    private static final String NASHORN_ENGINE_NAME = "nashorn";
    private ScriptEngine engine;
    private Invocable invoker;
    
    @Before
    public void init(){
        ScriptEngineManager manager = new ScriptEngineManager();
        //System.setProperty("nashorn.args", "--language=es6");
        //engine = manager.getEngineByName(NASHORN_ENGINE_NAME);
        engine = new NashornScriptEngineFactory().getScriptEngine("--language=es6");
        invoker = (Invocable)engine;
    }
        
    @Test
    public void ejecutar() throws ScriptException, FileNotFoundException, NoSuchMethodException{
        //engine.eval("Object.prototype");
        engine.eval(new FileReader("src/com/tracking/scripts/app.js"));
        invoker.invokeFunction("sendName", "nashorn");
    }
}
