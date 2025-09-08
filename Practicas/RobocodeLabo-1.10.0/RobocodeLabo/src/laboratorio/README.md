
# NicoustinRobot 

El robot implementa un sistema de estrategías dinámico que se adapta según el número de enemigos:
- **Start : EstrategiaWalls**
    - La estrategia se utiliza al empezar la partida o al haber muchos enemigos, el enfoque es que el robot sobreviva hasta que el número de robots restantes se reduzca lo suficiente.
- **End : EstrategiaAntiWalls**
    - En este caso el robot cambiará su patrón de reconocimiento a uno más enfocado en "cazar" a sus oponentes restantes.

Posee definido una estrategía actual, un evaluador de estrategias (encargado de analizar las condiciones del contexto en el que trasncurre y determinar la estrategía adecuada) y una llave que define la situación actual la cual está directamente relacionada a la situación actual.

El Strategy Pattern aparece en las dos estrategias utilizadas por el robot durante su ejecución y en el Evaluador de estrategias encargado de obtener la estrategía apropiada para el robot en el contexto que se encuentre, ambas mapeadas en el robot realizando los intercambios durante la ejecución del mismo.

- FieldDetector
  - Es una clase definida para determinar el tamaño total del campo de batalla. También existen dos estratégias para determinar el tamaño del área de batalla:
    - **Exploración por Rebote**: La ídea es que el robot explore el mapa y mida el campo usando rebotes contra las paredes para determinar el área total de esta misma, ayudandose con un contador en el que se almacenan la cantidad de rebotes y la distancia recorrida. Una vez realizado el cálculo el robot intentará volver a la posición inicial.
    - **Aproximación Inteligente**: El robot iniciará dirigiendose al norte hasta cumplir con la distancia que posee mapeada o hasta colisionar con la pared, luego intentará yendo al este. Según la cantidad de colisiones determinará el área del campo de batalla.

Una vez hecho el patrullaje del campo, el robot iniciará a posicionarse estrategicamente para evitar ser alcanzado por el fuego de los demás robots. Una vez el número de robots en el campo se reduzca a 3 se optará por la estrategía ofensiva (AntiWalls). El robot estará optando por buscar a los robots yendo a las esquinas y esperando para realizar una emboscada, si pasa mucho tiempo y no logra detectar otros robots cambiará e irá a otra esquina. En el caso de que sea alcanzado por un disparo también cambiará de posición. 

El robot combina dos estrategias según el contexto actual de la ronda. Por cada evento detectado por el robot, ya sea detectar, recibir impacto de un robot enemigo o colisionar con las paredes, el robot actualizará la estrategía que tiene mapeada en el momento.


// Entrega 2

Ahora en este caso el robot posee implementado una clase "Estratega" que se encargará de decidir cual es la mejor estrategia durante la partida según diferentes variantes (ya sea energia, cantidad de enemigos, etc).
El estratega se especifica en el constructor del robot y no se cambia durante la ejecución.