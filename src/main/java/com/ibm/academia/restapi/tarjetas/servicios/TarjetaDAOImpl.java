package com.ibm.academia.restapi.tarjetas.servicios;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.academia.restapi.tarjetas.enumeradores.TipoPassion;
import com.ibm.academia.restapi.tarjetas.modelo.entidades.Tarjeta;
import com.ibm.academia.restapi.tarjetas.repositorios.TarjetaRepository;

@Service
public class TarjetaDAOImpl implements TarjetaDAO {

	private TarjetaRepository tarjetaRepository;

	@Autowired
	public TarjetaDAOImpl(TarjetaRepository tarjetaRepository) {
		this.tarjetaRepository = tarjetaRepository;
	}
	
	@Override
	@Transactional(readOnly = true)
	public Optional<Tarjeta> buscarPorId(Long id) {
		return tarjetaRepository.findById(id);
	}

	@Override
	@Transactional
	public Tarjeta guardar(Tarjeta tarjeta) {
		return tarjetaRepository.save(tarjeta);
	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<Tarjeta> buscarTodos() {
		return tarjetaRepository.findAll();
	}

	@Override
	@Transactional
	public void eliminarPorId(Long id) {
		tarjetaRepository.deleteById(id);
	}

	@Override
	@Transactional
	public Iterable<Tarjeta> guardarVarios(Iterable<Tarjeta> tarjetas) {
		return tarjetaRepository.saveAll(tarjetas);
	}
	

	@Override
	@Transactional(readOnly = true)
	public Iterable<Tarjeta> findTarjetasByClienteId(Long clienteId) {
		return tarjetaRepository.findTarjetasByClienteId(clienteId);
	}
	
	
	@Override
	public String obtenerTipoTarjeta(TipoPassion passion, BigDecimal salario, Integer edad) {
		String tipo = "";
		if (passion == TipoPassion.SHOPPING) {
			tipo = tipoShopping(salario, edad);
			return tipo;
		}else if (passion == TipoPassion.TRAVELS) {
			tipo = tipoTravels(salario, edad);
			return tipo;
		}else if (passion == TipoPassion.HELP) {
			tipo = tipoHelp(salario, edad);
			return tipo;
		} else if(passion == TipoPassion.BUSINESS) {
			tipo = tipoBusiness(salario, edad);
			return tipo;
		}else if(passion == TipoPassion.SPORTS) {
			tipo = tipoSports(salario, edad);
			return tipo;
		}else if(passion == TipoPassion.STYLE) {
			tipo = tipoStyle(salario, edad);
			return tipo;
		}
		return tipo;
	}

	private String tipoShopping(BigDecimal salario, Integer edad) {
		String tipo = "";
		if (salario.doubleValue() >= 7000 && salario.doubleValue() <= 14999) {
			if (edad >= 18 && edad <= 23) {
				tipo = "B+Smart, Afinity card";
				return tipo;
			} else if (edad >= 24 && edad <= 32) {
				tipo = "B+Smart, Afinity card, Office Deppot, Costco, Best Buy Banamex, The home depot";
				return tipo;
			} else if (edad >= 33 && edad <= 75) {
				tipo = "clasica, Office Deppot, Costco, Best Buy Banamex, The home depot";
				return tipo;
			}else {
				tipo = "No hay tarjetas disponibles para esta edad";
				return tipo;
			}
		} else if (salario.doubleValue() >= 15000 && salario.doubleValue() <= 34999) {
			if (edad >= 18 && edad <= 23) {
				tipo = "B+Smart, Afinity card";
				return tipo;
			} else if (edad >= 24 && edad <= 32) {
				tipo = "Oro, Afinity card, Office Deppot, Costco, Best Buy Banamex, The home depot";
				return tipo;
			} else if (edad >= 33 && edad <= 75) {
				tipo = "Oro, Office Deppot, Costco, Best Buy Banamex, The home depot";
				return tipo;
			}else {
				tipo = "No hay tarjetas disponibles para esta edad";
				return tipo;
			}
		} else if (salario.doubleValue() >= 35000 && salario.doubleValue() <= 49999) {
			if (edad >= 18 && edad <= 23) {
				tipo = "B+Smart, Afinity card";
				return tipo;
			} else if (edad >= 24 && edad <= 32) {
				tipo = "Platinum, Afinity card, Office Deppot, Costco, Best Buy Banamex, The home depot";
				return tipo;
			} else if (edad >= 33 && edad <= 75) {
				tipo = "No hay tarjetas disponibles para esta edad";
				return tipo;
			}else {
				tipo = "No hay tarjetas disponibles para esta edad";
				return tipo;
			}
		} else if (salario.doubleValue() >= 50000) {
			if (edad >= 18 && edad <= 23) {
				tipo = "B+Smart, Afinity card";
				return tipo;
			} else if (edad >= 24 && edad <= 75) {
				tipo = "Platinum";
				return tipo;
			} else {
				tipo = "No hay tarjetas disponibles para esta edad";
				return tipo;
			}
		}
		return tipo;
	}
	
	private String tipoTravels(BigDecimal salario, Integer edad) {
		String tipo = "";
		if (salario.doubleValue() >= 12000 && salario.doubleValue() <= 29999) {
			if (edad >= 18 && edad <= 75) {
				tipo = "Citi /AAdvantage";
				return tipo;
			} else {
				tipo = "No hay tarjetas disponibles para esta edad";
				return tipo;
			}
		}else if (salario.doubleValue() >= 30000 && salario.doubleValue() <= 34999) {
			if (edad >= 18 && edad <= 23) {
				tipo = "Citi /AAdvantage";
				return tipo;
			} else if (edad >= 24 && edad <= 75) {
				tipo = "Citi /AAdvantage, Platinum";
				return tipo;
			}else {
				tipo = "No hay tarjetas disponibles para esta edad";
				return tipo;
			}
		}else if (salario.doubleValue() >= 33000 && salario.doubleValue() <= 49999) {
			if (edad >= 18 && edad <= 23) {
				tipo = "Citi /AAdvantage";
				return tipo;
			} else if (edad >= 24 && edad <= 75) {
				tipo = "Citi /AAdvantage, Platinum";
				return tipo;
			}else {
				tipo = "No hay tarjetas disponibles para esta edad";
				return tipo;
			}
		}else if (salario.doubleValue() >= 50000) {
			if (edad >= 18 && edad <= 22) {
				tipo = "Citi /AAdvantage";
				return tipo;
			} else if (edad >= 23 && edad <= 75) {
				tipo = "Platinum";
				return tipo;
			} else {
				tipo = "No hay tarjetas disponibles para esta edad";
				return tipo;
			}
		}
		return tipo;
	}
	
	public String tipoHelp(BigDecimal salario, Integer edad) {
		String tipo = "";
		if(salario.doubleValue()>=12000) {
			if(edad>=18 && edad<= 75) {
				tipo = "Banamex, Teletón, APAC";
			}else {
				tipo = "No hay tarjetas disponibles para esta edad";
				return tipo;
			}
		}
		return tipo;
	}

	private String tipoBusiness(BigDecimal salario, Integer edad) {
		String tipo = "";
		if(salario.doubleValue()>=7000) {
			if(edad>=18 && edad<= 75) {
				tipo = "Office depot, costco, BestBuy Banamex, The home depot";
			}else {
				tipo = "No hay tarjetas disponibles para esta edad";
				return tipo;
			}
		}
		return tipo;
	}
	
	private String tipoSports(BigDecimal salario, Integer edad) {
		String tipo = "";
		if(salario.doubleValue()>=7000 && salario.doubleValue()<=34999) {
			if(edad>=18 && edad<= 75) {
				tipo = "Martí Clásica Citibanamex, América Deporteísmo, Toluca Deporteismo, Pumas Deporteismo, La verde Deporteismo";
			}else {
				tipo = "No hay tarjetas disponibles para esta edad";
				return tipo;
			}
		}if(salario.doubleValue()>=35000) {
			if(edad>=18 && edad<= 75) {
				tipo = "Martí Clásica Citibanamex";
			}else {
				tipo = "No hay tarjetas disponibles para esta edad";
				return tipo;
			}
		}
		return tipo;
	}
	
	public String tipoStyle(BigDecimal salario, Integer edad) {
		String tipo = "";
		if (salario.doubleValue() >= 7000 && salario.doubleValue() <= 14999) {
			if (edad >= 18 && edad <= 32) {
				tipo = "B+Smart, Afinity card";
				return tipo;
			} else if (edad >= 33 && edad <= 75) {
				tipo = "B+Smart";
				return tipo;
			} else {
				tipo = "No hay tarjetas disponibles para esta edad";
				return tipo;
			}
		} else if (salario.doubleValue() >= 15000 && salario.doubleValue() <= 29999) {
			if (edad >= 18 && edad <= 23) {
				tipo = "B+Smart, Afinity card";
				return tipo;
			} else if (edad >= 24 && edad <= 32) {
				tipo = "B+Smart, Afinity card, Oro";
				return tipo;
			}else if (edad >= 33 && edad <= 75) {
				tipo = "Oro";
				return tipo;
			}else {
				tipo = "No hay tarjetas disponibles para esta edad";
				return tipo;
			}
		} else if (salario.doubleValue() >= 30000 && salario.doubleValue() <= 34999) {
			if (edad >= 18 && edad <= 23) {
				tipo = "B+Smart, Afinity card";
				return tipo;
			} else if (edad >= 24 && edad <= 32) {
				tipo = "B+Smart, Afinity card, Platinum";
				return tipo;
			}else if (edad >= 33 && edad <= 75) {
				tipo = "Platinum";
				return tipo;
			}else {
				tipo = "No hay tarjetas disponibles para esta edad";
				return tipo;
			}
		} else if (salario.doubleValue() >= 35000) {
			if (edad >= 18 && edad <= 23) {
				tipo = "B+Smart";
				return tipo;
			} else if (edad >= 24 && edad <= 75) {
				tipo = "B+Smart, Platinum";
				return tipo;
			} else {
				tipo = "No hay tarjetas disponibles para esta edad";
				return tipo;
			}
		}
		return tipo;
	}

}
