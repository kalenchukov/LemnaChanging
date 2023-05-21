/**
 * Определяет API для изменения значений полей классов.
 */
module dev.kalenchukov.lemna.changing
{
	requires org.jetbrains.annotations;
	requires org.apache.logging.log4j;

	exports dev.kalenchukov.lemna.changing;
	exports dev.kalenchukov.lemna.changing.exceptions;
	exports dev.kalenchukov.lemna.changing.annotations;
	exports dev.kalenchukov.lemna.changing.interfaces;
}