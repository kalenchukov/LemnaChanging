/*
 * Copyright © 2022 Алексей Каленчуков
 * GitHub: https://github.com/kalenchukov
 * E-mail: mailto:aleksey.kalenchukov@yandex.ru
 */

package dev.kalenchukov.lemna.changing.interfaces;

import org.jetbrains.annotations.Nullable;

/**
 * Интерфейс для реализации собственного класса изменяющего значение поля.
 *
 * @param <T> Объект типа значения поля.
 */
public interface Modificatory<T>
{
	/**
	 * Изменяет значение поля.
	 *
	 * @param value Значение поля, которое необходимо изменить.
	 * @return Возвращает изменённое значение поля.
	 */
	@Nullable
	T modify(@Nullable T value);
}
