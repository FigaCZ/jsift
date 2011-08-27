/*
 * Copyright 2011 Stefan C. Mueller.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.smurn.jsift;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.smurn.jsift.TestUtils.*;

/**
 * Unit tests for {@link Interpolator}.
 */
public class LinearUpScalerImplTest {

    @Test(expected = NullPointerException.class)
    public void nullImage() {
        LinearUpScalerImpl target = new LinearUpScalerImpl();
        target.upScale(null);
    }

    @Test
    public void zeroImage() {
        LinearUpScalerImpl target = new LinearUpScalerImpl();
        Image actual = target.upScale(new Image(0, 10));
        Image expected = new Image(0, 19);
        assertThat(actual, TestUtils.equalTo(expected, 1E-10f));
    }

    @Test
    public void oneByOne() {
        LinearUpScalerImpl target = new LinearUpScalerImpl();
        Image actual = target.upScale(new Image(new float[][]{{0.4f}}));
        Image expected = new Image(new float[][]{{0.4f}});
        assertThat(actual, TestUtils.equalTo(expected, 1E-10f));
    }

    @Test
    public void interpolate() {
        Image input = new Image(new float[][]{
                    {1.0f, 2.0f, 3.0f},
                    {4.0f, 5.0f, 6.0f}
                });

        Image expected = new Image(new float[][]{
                    {1.0f, 1.5f, 2.0f, 2.5f, 3.0f},
                    {2.5f, 3.0f, 3.5f, 4.0f, 4.5f},
                    {4.0f, 4.5f, 5.0f, 5.5f, 6.0f}
                });

        LinearUpScalerImpl target = new LinearUpScalerImpl();
        Image actual = target.upScale(input);

        assertThat(actual, equalTo(expected, 1E-10f));
    }
}