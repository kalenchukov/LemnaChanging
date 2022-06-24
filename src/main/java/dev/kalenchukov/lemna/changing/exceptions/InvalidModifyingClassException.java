/*
 * Copyright © 2022 Алексей Каленчуков
 * GitHub: https://github.com/kalenchukov
 * E-mail: mailto:aleksey.kalenchukov@yandex.ru
 */

package dev.kalenchukov.lemna.changing.exceptions;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Исключение для некорректного изменяющего значение поля.
 */
public class InvalidModifyingClassException extends RuntimeException
{
    /**
     * Конструктор для {@code InvalidChangerException}.
     *
     * @param message Сообщение.
     */
    public InvalidModifyingClassException(@NotNull final String message)
    {
        super(Objects.requireNonNull(message));
    }
}
