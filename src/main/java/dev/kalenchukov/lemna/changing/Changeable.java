/*
 * Copyright © 2022 Алексей Каленчуков
 * GitHub: https://github.com/kalenchukov
 * E-mail: mailto:aleksey.kalenchukov@yandex.ru
 */

package dev.kalenchukov.lemna.changing;

import dev.kalenchukov.lemna.changing.exceptions.InvalidModifyingClassException;
import org.jetbrains.annotations.NotNull;

import java.util.Locale;

/**
 * Интерфейс для реализации изменяющего значения полей.
 */
public interface Changeable
{
	/**
	 * Устанавливает локализацию.
	 *
	 * @param locale Локализация.
	 */
	void setLocale(@NotNull Locale locale);

	/**
	 * Изменяет значение поля.
	 *
	 * @throws InvalidModifyingClassException Если изменяющий класс некорректный.
	 */
	void change() throws InvalidModifyingClassException;
}
