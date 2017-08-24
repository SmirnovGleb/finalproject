package test.by.epam.roulette.suite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import test.by.epam.roulette.converter.TestMD5Converter;
import test.by.epam.roulette.logic.TestCalculateWinnings;
import test.by.epam.roulette.logic.TestMakeArrayBets;
import test.by.epam.roulette.validator.TestUserParametersValidator;

/**
 * The Class TestRoulette.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ TestMD5Converter.class, TestCalculateWinnings.class, TestMakeArrayBets.class, TestUserParametersValidator.class })
public class TestRoulette {

}
