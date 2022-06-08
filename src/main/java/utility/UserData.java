package utility;

import java.io.Serializable;

public class UserData implements Serializable {
    private String login;
    private String password;

    public UserData(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
/**
login=Iniciar sessió
        password=Contrasenya
        enter=Accedir
        registration=Inscripció
        table=Taula
        visualisation=Visualització
        executeScript=Executa L ' Script
        clear=Neteja
        info=Informació
        help=Ajuda
        printFieldDescendingStartDate=Imprimeix el camp descendent data d ' inici
        filterGreaterThanPosition=Filtra més gran que la posició
        replaceIfLowe=Substitueix Si
        replaceIfGreater=Substitueix si És més gran
        insert=Insereix
        countByStartDate=Comptar per data D'Inici
        removeKey=Elimina la clau
        removeGreater=Elimina major
        update=Actualitza
        exit=Sortida
        key=Clau
        name=Nom
        creationDate=Data de creació
        salary=Salari
        startDate=Data d'inici
        position=Posició
        status=Estat
        employeesCount=Nombre d'empleats
        type=Tipus
        postalAddress=Adreça postal
        creator=Creador
        #submit=ca_ES


        login=Sesión
        password=Contraseña
        enter=Entrar
        registration=Registro
        table=Tabla
        visualisation=Visualizaci
        executeScript=Ejecutar script
        clear=Claro
        info=Info
        help=Ayudar
        printFieldDescendingStartDate=Fecha de inicio descendente del campo de impresión
        filterGreaterThanPosition=Filtro mayor que la posición
        replaceIfLowe=Reemplace si es bajo
        replaceIfGreater=Reemplazar si es mayor
        insert=Insertar
        countByStartDate=Recuento por fecha de inicio
        removeKey=quitar clave
        removeGreater=quitar mayor
        update=Actualizar
        exit=Salida
        key=Clave
        name=Nombre
        creationDate=Fecha de creación
        salary=Salario
        startDate=Fecha de inicio
        position=Posición
        status=Estatus
        employeesCount=Recuento de empleados
        type=Tipo
        postalAddress=Dirección postal
        creator=Creador
        #submit=es_sV

        login=Login
        password=Parool
        enter=Sisestama
        registration=Registreerimine
        table=Tabel
        visualisation=Visualiseerimine
        executeScript=Käivita skript
        clear=Selge
        info=Info
        help=Aitama
        printFieldDescendingStartDate=Prindi väli kahanev alguskuupäev
        filterGreaterThanPosition=Filter suurem kui positsioon
        replaceIfLowe=Asenda, kui Lowe
        replaceIfGreater=Asenda, kui see on suurem
        insert=lisada
        countByStartDate=Loendage alguskuupäeva järgi
        removeKey=Eemalda võti
        removeGreater=Eemalda suurem
        update=Värskendus
        exit=Väljumine
        key=Võti
        name=Nimi
        creationDate=Koostamise kuupäev
        salary=Palk
        startDate=Alguskuupäev
        position=Seisukoht
        status=Staatus
        employeesCount=Töötajate arv
        type=Tüüp
        postalAddress=Postiaadress
        creator=Looja
        #submit=et_EE

 login=Логин
 password=Пароль
 enter=Войти
 registration=Зерег

 **/