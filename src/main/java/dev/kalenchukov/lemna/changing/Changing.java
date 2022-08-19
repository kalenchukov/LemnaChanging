/*
 * Copyright 2022 Алексей Каленчуков
 * GitHub: https://github.com/kalenchukov
 * E-mail: mailto:aleksey.kalenchukov@yandex.ru
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.kalenchukov.lemna.changing;

import dev.kalenchukov.lemna.changing.exceptions.InvalidModifyingClassException;
import dev.kalenchukov.lemna.changing.interfaces.Modificatory;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Класс для изменения значений полей класса.
 */
public class Changing implements Changeable
{
	/**
	 * Локализация.
	 */
	@NotNull
	private Locale locale = new Locale("ru", "RU");

	/**
	 * Объект класса в котором необходимо изменить значения полей.
	 */
	@NotNull
	private final Object object;

	/**
	 * Логгер для данного класса.
	 */
	@NotNull
	private static final Logger LOG = Logger.getLogger(Changing.class);

	/**
	 * Локализованные тексты логирования.
	 */
	@NotNull
	private ResourceBundle localeLogs = ResourceBundle.getBundle(
		"lemna/changing/localizations/logs",
		this.locale
	);

	/**
	 * Локализованные тексты исключений.
	 */
	@NotNull
	private ResourceBundle localeExceptions = ResourceBundle.getBundle(
		"lemna/changing/localizations/exceptions",
		this.locale
	);

	/**
	 * Конструктор для {@code Changing}.
	 *
	 * @param object Объект класса в котором необходимо изменить значения полей.
	 */
	public Changing(@NotNull final Object object)
	{
		Objects.requireNonNull(object);

		this.object = object;
	}

	/**
	 * @see Changing#setLocale(Locale)
	 */
	public void setLocale(@NotNull Locale locale)
	{
		Objects.requireNonNull(locale);

		if (!this.locale.equals(locale))
		{
			this.locale = locale;

			localeLogs = ResourceBundle.getBundle(
				"lemna/changing/localizations/logs",
				this.locale
			);

			localeExceptions = ResourceBundle.getBundle(
				"lemna/changing/localizations/exceptions",
				this.locale
			);
		}
	}

	/**
	 * @see Changing#change()
	 */
	public void change() throws InvalidModifyingClassException
	{
		LOG.debug(String.format(
			localeLogs.getString("60001"),
			this.object.getClass().getName()
		));

		for (Field field : this.object.getClass().getDeclaredFields())
		{
			dev.kalenchukov.lemna.changing.annotations.Changer[] annotationsChanger = field.getAnnotationsByType(
				dev.kalenchukov.lemna.changing.annotations.Changer.class);

			if (annotationsChanger.length == 0) {
				continue;
			}

			field.setAccessible(true);

			this.changeValueField(field, annotationsChanger);

			field.setAccessible(false);
		}

		LOG.debug(String.format(
			localeLogs.getString("60002"),
			this.object.getClass().getName()
		));
	}

	/**
	 * Изменяет значение поля класса.
	 *
	 * @param field Поле класса в котором необходимо изменить значение.
	 * @param annotationsChanger Аннотации {@code Changer} применяемые к полю класса.
	 *
	 * @throws InvalidModifyingClassException Если изменяющий некорректный.
	 */
	private void changeValueField(@NotNull final Field field, @NotNull dev.kalenchukov.lemna.changing.annotations.Changer @NotNull [] annotationsChanger)
		throws InvalidModifyingClassException
	{
		Objects.requireNonNull(field);
		Objects.requireNonNull(annotationsChanger);

		for (dev.kalenchukov.lemna.changing.annotations.Changer annotationChanger : annotationsChanger)
		{
			Class<? extends Modificatory<?>> changer = annotationChanger.modifier();

			LOG.debug(String.format(
				localeLogs.getString("60003"),
				changer.getName(),
				field.getName()
			));

			try
			{
				Method method = changer.getDeclaredMethod("modify", field.getType());

				field.set(
					this.object,
					method.invoke(
						changer.getConstructor().newInstance(),
						field.get(this.object)
					)
				);
			}
			catch (Exception exception)
			{
				throw new InvalidModifyingClassException(String.format(
					localeExceptions.getString("70001"),
					changer.getName(),
					this.object.getClass().getName()
				));
			}

			LOG.debug(String.format(
				localeLogs.getString("60004"),
				field.getName()
			));
		}
	}
}
