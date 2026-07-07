\# Documentación de la Base de Datos



\## Descripción General



La base de datos está diseñada para gestionar la información académica de una institución educativa. Permite administrar estudiantes, apoderados, docentes, cursos, grados, matrículas, horarios y los diferentes años académicos.



El diseño se encuentra dividido en varios módulos para facilitar la organización de la información:



\- Gestión de estudiantes.

\- Gestión de docentes.

\- Gestión académica.

\- Gestión de grados y cursos.

\- Gestión de salones.

\- Gestión de horarios.



\---



\# Módulos de la Base de Datos



\## 1. Módulo de Estudiantes



Este módulo almacena la información personal de los estudiantes y sus apoderados.



\### Tabla: `estudiantes`



Almacena los datos personales de cada estudiante.



| Campo | Tipo | Descripción |

|--------|------|-------------|

| id\_estudiante | int | Identificador único del estudiante. |

| dni | char(8) | Documento Nacional de Identidad. |

| nombres | varchar(100) | Nombres del estudiante. |

| apellido\_paterno | varchar | Apellido paterno. |

| apellido\_materno | varchar | Apellido materno. |

| fecha\_nacimiento | date | Fecha de nacimiento. |

| sexo | enum | Género del estudiante. |

| direccion | varchar(255) | Dirección domiciliaria. |

| telefono | varchar(20) | Número telefónico. |

| estado | boolean | Estado activo/inactivo. |

| fecha\_registro | timestamp | Fecha de registro del estudiante. |



\---



\### Tabla: `apoderados`



Contiene la información de los padres o tutores responsables del estudiante.



| Campo | Tipo | Descripción |

|--------|------|-------------|

| id\_apoderado | int | Identificador del apoderado. |

| dni | char(8) | DNI del apoderado. |

| nombres | varchar(100) | Nombres completos. |

| apellido\_paterno | varchar | Apellido paterno. |

| apellido\_materno | varchar | Apellido materno. |

| telefono | varchar(20) | Número telefónico. |

| direccion | varchar(255) | Dirección. |



\---



\### Tabla: `estudiante\_apoderado`



Tabla puente que relaciona estudiantes con sus apoderados.



Un estudiante puede tener varios apoderados y un apoderado puede estar relacionado con varios estudiantes.



| Campo | Tipo | Descripción |

|--------|------|-------------|

| id\_estudiante | int | Estudiante relacionado. |

| id\_apoderado | int | Apoderado relacionado. |

| parentesco | varchar(50) | Relación familiar (Padre, Madre, Tutor, etc.). |



\---



\## 2. Módulo de Docentes



Este módulo almacena la información del personal docente.



\### Tabla: `docentes`



| Campo | Tipo | Descripción |

|--------|------|-------------|

| id\_docente | int | Identificador del docente. |

| dni | char(8) | DNI. |

| nombres | varchar(100) | Nombres completos. |

| apellido\_paterno | varchar | Apellido paterno. |

| apellido\_materno | varchar | Apellido materno. |

| especialidad | varchar(100) | Especialidad del docente. |

| telefono | varchar(20) | Número telefónico. |

| correo | varchar(100) | Correo electrónico. |

| estado | boolean | Estado laboral. |



\---



\## 3. Módulo Académico



Este módulo controla los años académicos y la asignación de docentes a cursos.



\### Tabla: `anios\_academicos`



Representa cada periodo escolar.



| Campo | Tipo | Descripción |

|--------|------|-------------|

| id\_anio | int | Identificador del año académico. |

| anio | year | Año escolar. |

| fecha\_inicio | date | Inicio del periodo. |

| fecha\_fin | date | Fin del periodo. |

| activo | boolean | Indica si el periodo está vigente. |



\---



\### Tabla: `docente\_curso`



Relaciona un docente con un curso y un grado durante un determinado año académico.



| Campo | Tipo | Descripción |

|--------|------|-------------|

| id\_docente\_curso | int | Identificador. |

| id\_docente | int | Docente asignado. |

| id\_grado\_curso | int | Curso asignado al grado. |

| id\_anio | int | Año académico. |



\---



\## 4. Módulo de Grados y Cursos



Este módulo organiza los grados escolares y los cursos que pertenecen a cada uno.



\### Tabla: `grados`



Contiene los grados educativos.



Ejemplos:



\- Primer grado

\- Segundo grado

\- Tercer grado



| Campo | Tipo |

|--------|------|

| id\_grado | int |

| nombre | varchar(50) |



\---



\### Tabla: `cursos`



Contiene el catálogo de cursos.



Ejemplos:



\- Matemática

\- Comunicación

\- Ciencia y Tecnología



| Campo | Tipo |

|--------|------|

| id\_curso | int |

| codigo | varchar(20) |

| nombre | varchar(100) |

| horas\_semanales | int |



\---



\### Tabla: `grado\_curso`



Tabla intermedia que determina qué cursos pertenecen a cada grado.



Esto permite que un mismo curso pueda enseñarse en distintos grados.



| Campo | Tipo |

|--------|------|

| id\_grado\_curso | int |

| id\_grado | int |

| id\_curso | int |



\---



\## 5. Módulo de Salones



\### Tabla: `salones`



Representa las aulas físicas de la institución.



| Campo | Tipo | Descripción |

|--------|------|-------------|

| id\_salon | int | Identificador. |

| codigo | varchar(20) | Código del salón. |

| nombre | varchar(50) | Nombre del aula. |

| capacidad | int | Número máximo de estudiantes. |

| id\_grado | int | Grado al que pertenece el salón. |



\---



\## 6. Módulo de Matrículas



\### Tabla: `matriculas`



Registra la matrícula de cada estudiante durante un año académico.



Cada estudiante puede matricularse una vez por año.



| Campo | Tipo |

|--------|------|

| id\_matricula | int |

| id\_estudiante | int |

| id\_anio | int |

| id\_grado | int |

| id\_salon | int |

| fecha\_matricula | date |



\---



\## 7. Módulo de Horarios



\### Tabla: `horarios`



Define los horarios de clases.



Cada registro indica:



\- Docente

\- Curso

\- Salón

\- Día de la semana

\- Hora de inicio

\- Hora de fin



| Campo | Tipo |

|--------|------|

| id\_horario | int |

| id\_docente\_curso | int |

| id\_salon | int |

| dia\_semana | enum |

| hora\_inicio | time |

| hora\_fin | time |



\---



\# Relaciones Principales



\## Estudiantes



```

Estudiantes

&#x20;     │

&#x20;     ├──────── Matrículas

&#x20;     │

&#x20;     └──────── Estudiante\_Apoderado ─────── Apoderados

```



\---



\## Docentes



```

Docentes

&#x20;     │

&#x20;     │

Docente\_Curso

&#x20;     │

&#x20;     ├──────── Año Académico

&#x20;     └──────── Grado\_Curso

```



\---



\## Organización Académica



```

Grados

&#x20;  │

&#x20;  ├──────── Salones

&#x20;  │

&#x20;  └──────── Grado\_Curso

&#x20;                │

&#x20;                └──────── Cursos

```



\---



\## Horarios



```

Docente\_Curso

&#x20;       │

&#x20;       ├──────── Horarios

&#x20;       │

&#x20;       └──────── Salones

```



\---



\# Flujo General del Sistema



```text

Registrar Apoderado

&#x20;         │

&#x20;         ▼

Registrar Estudiante

&#x20;         │

&#x20;         ▼

Relacionar Estudiante - Apoderado

&#x20;         │

&#x20;         ▼

Registrar Año Académico

&#x20;         │

&#x20;         ▼

Crear Grados

&#x20;         │

&#x20;         ▼

Crear Cursos

&#x20;         │

&#x20;         ▼

Asignar Cursos a Grados

&#x20;         │

&#x20;         ▼

Registrar Docentes

&#x20;         │

&#x20;         ▼

Asignar Docente al Curso

&#x20;         │

&#x20;         ▼

Crear Salones

&#x20;         │

&#x20;         ▼

Matricular Estudiante

&#x20;         │

&#x20;         ▼

Crear Horarios

```



\---



\# Ventajas del Diseño



\- Base de datos normalizada para evitar duplicidad de información.

\- Permite reutilizar cursos en diferentes grados.

\- Soporta múltiples años académicos.

\- Permite que un estudiante tenga uno o varios apoderados.

\- Facilita la asignación de docentes por curso y año académico.

\- Permite administrar horarios sin duplicar información.

\- Escalable para incorporar nuevas funcionalidades como asistencia, notas, evaluaciones o pagos.



\---



\# Resumen de Relaciones



| Tabla | Relación |

|--------|----------|

| estudiantes → matrículas | Uno a muchos |

| estudiantes ↔ apoderados | Muchos a muchos |

| grados ↔ cursos | Muchos a muchos |

| docentes → docente\_curso | Uno a muchos |

| anios\_academicos → docente\_curso | Uno a muchos |

| docente\_curso → horarios | Uno a muchos |

| salones → horarios | Uno a muchos |

| grados → salones | Uno a muchos |

| estudiantes → matrículas | Uno a muchos |

| salones → matrículas | Uno a muchos |

| anios\_academicos → matrículas | Uno a muchos |

