package com;

import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;

import com.example.Veri;

import selenium.ParallelSuite;

/**
 * @author Sinan
 * 
 *
 */
@RunWith(ParallelSuite.class)
@SuiteClasses({ Veri.class})
public class Tests {
}