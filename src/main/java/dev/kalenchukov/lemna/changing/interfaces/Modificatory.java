/*
 * Copyright © 2022-2023 Алексей Каленчуков
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

package dev.kalenchukov.lemna.changing.interfaces;

import org.jetbrains.annotations.Nullable;

/**
 * Интерфейс для реализации собственного класса изменяющего значение поля.
 *
 * @param <V> тип значения поля.
 * @author Алексей Каленчуков
 */
public interface Modificatory<V>
{
	/**
	 * Изменяет значение поля.
	 *
	 * @param value значение поля, которое необходимо изменить.
	 * @return возвращает изменённое значение поля.
	 */
	@Nullable
	V modify(@Nullable V value);
}
