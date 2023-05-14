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

package dev.kalenchukov.lemna.changing.annotations;

import dev.kalenchukov.lemna.changing.interfaces.Modificatory;
import org.jetbrains.annotations.NotNull;

import java.lang.annotation.*;

/**
 * Позволяет изменять значение поля класса.
 *
 * @author Алексей Каленчуков
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Repeatable(Changer.ManyChanger.class)
public @interface Changer
{
	/**
	 * Задаёт класс изменяющего значение поля.
	 *
	 * @return класс изменяющего значение поля.
	 */
	@NotNull
	Class<? extends Modificatory<?>> modifier();

	/**
	 * Множество аннотаций {@code Changer}.
	 *
	 * @author Алексей Каленчуков
	 */
	@Documented
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.FIELD)
	@interface ManyChanger
	{
		/**
		 * Задаёт множество {@code Changer}.
		 *
		 * @return массив из {@code Changer}.
		 */
		@NotNull
		Changer @NotNull [] value();
	}
}
