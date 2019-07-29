package helpdesk;

public abstract class Lang{
    
    public abstract class Spanish{
        
        public abstract class Functions{
            public static final String ERROR = "Oops!";
            public static final String OK = "OK";
        }
    
        public abstract class Login{
            
            public static final String USERNAME="Usuario";
            public static final String PASSWORD="Contraseña";
            public static final String LOGIN ="Iniciar sesión";
            
                public abstract class Error{
                    public static final String TITLE="Oops!";
                    public static final String NULLUSERNAME="El nombre de usuario está vacío.";
                    public static final String NULLPASSWORD="La contraseña está vacía.";
                    public static final String ILEGALUSERNAME="El usuario sólo puede tener letras.";
                    public static final String ILEGALDATA="Usuario y/o contraseña no válidos.";
                    public static final String NOCLIENT="Su cuenta se encuentra vencida.";
                    public static final String SHORTPASSWORD ="La contraseña debe tener mínimo 8 caracteres.";
                }
                
            

        }
        
        public abstract class Logout{
            public static final String LOGOUT_CONFIRM="¿Estás seguro que deseas cerrar sesión?";
        }

        public abstract class Home{
            public static final String TITLE ="Menú principal";
            public static final String TICKETS = "Mis Tickets";
            public static final String NEW_TICKET = "Crear Ticket";
            public static final String FAQS = "FAQ's";
            public static final String DATA = "Mis datos";
            public static final String LOGOUT = "Cerrar sesión";	
        }

        public abstract class TicketForm{
        	public abstract class Priority{
        		public static final String PRIORITY = "Prioridad";
        		public static final String LOW = "Baja";
        		public static final String NORMAL = "Normal";
        		public static final String HIGH = "Alta";
                        public static final String CONFIRMATION = "¿En realidad es muy urgente?";
                        public static final String CONFIRMATION_TITLE = "Confirmar prioridad";
        	}

        	public abstract class Buttons{
        		public static final String SEND = "Enviar";
        		public static final String CANCEL = "Cancelar";
        	}

        	public static final String TOPIC = "Asunto";
        	public static final String CONTENT = "Descripción del problema";
        	public static final String TIP = "Antes de crear un ticket, te recomendamos leer las FAQ's, posiblemente ahí encuentres una solución más rápido.";
                public static final String CONTENT_TIP = "Escriba el detalle del problema, sea descriptivo y claro por favor.";
                public static final String SUCCESS = "El ticket ha sido agregado, en breve recibirá una respuesta";
                public static final String BLANK_CATEGORY = "Seleccionar:";
                
                public abstract class Error{
                            public static final String NULL_TOPIC = "El campo asunto está vacío";
                            public static final String NULL_CONTENT = "El campo descripción está vacío";
                            public static final String INVALID_TOPIC = "El asunto sólo puede contener letras";
                            public static final String NULL_CATEGORY = "Por favor selecciona una categoría";
                }
        
        }

        public abstract class Register{

        }
        
        public abstract class Data{
            
            public static final String NAME = "Nombre";
            public static final String USER = "Nombre de usuario";
            public static final String EMAIL = "Email";
            public static final String BIRTHDAY = "Fecha de nacimiento";
            public static final String GENDER = "Sexo";
            public static final String MEN = "Hombre";
            public static final String WOMEN = "Mujer";
            public static final String ADDRESS = "Domicilio";
            public static final String FCR = "RFC";
            public static final String OK = "OK";
            public static final String YEAR = "Año";
            public static final String MONTH = "Mes";
            public static final String DAY = "Día";
            public static final String SAVE = "Guardar";
            public static final String ADDR_TIP = "Calle, número, colonia, código postal, ciudad y estado";
            
            public abstract class Error{
                public static final String NULL_NAME = "El campo nombre está vacío";
                public static final String INVALID_NAME = "El nombre no tiene formato válido";
                public static final String NULL_EMAIL = "El campo email está vacío";
                public static final String INVALID_EMAIL = "El email no tiene formato válido";
                public static final String NULL_ADDRESS = "El campo dirección está vacío";
                public static final String INVALID_ADDRESS = "La dirección sólo puede tener letras, números, guiones, puntos y el símbolo gato"; 
                public static final String E_500 = "Hubo un problema al procesar lo solicitado";
                public static final String NULL_GENDER = "Debes elegir un sexo";
                public static final String INVALID_DATE = "La fecha no es válida";
            }
        }
        
        public abstract class AdminPanel{
            public static final String TITLE = "Panel de Administración"; 
            public abstract class Menu{
                public static final String TICKETS = "Tickets";
                public static final String DATA = "Mis datos";
                public static final String LOGOUT ="Cerrar sesión";
                public static final String USERS = "Usuarios";
                public static final String TECHS = "Técnicos";
                
            }
        }
        
        public abstract class TechPanel{
            public static final String TITLE ="Equipo técnico";
        }
    
    }
    
    public abstract class English{
        public abstract class Functions{
            public static final String ERROR = "Oops!";
            public static final String OK = "OK";
        }
    
        public abstract class Login{
                public abstract class Error{
                    public static final String TITLE="Oops!";
                    public static final String NULLUSERNAME="The username field is empty.";
                    public static final String NULLPASSWORD="The password field is empty.";
                    public static final String ILEGALUSERNAME="The username field only allows letters.";
                    public static final String ILEGALDATA="User and/or password incorrect.";
                    public static final String NOCLIENT="Your account was suspended.";
                }
                
            public static final String USERNAME="Username";
            public static final String PASSWORD="Password";
            public static final String LOGIN ="Login";

        }

        public abstract class Home{
            public static final String TITLE ="Home";
            public static final String TICKETS = "My tickets";
            public static final String NEW_TICKET = "Submit Ticket";
            public static final String FAQS = "FAQ'";
            public static final String DATA = "My data";
            public static final String LOGOUT = "Logout";
        }
    }
    
    public abstract class French{
    
    }
    
    public abstract class German{
    
    }
    
    public abstract class Portuguese{
    
    }
    
    
}