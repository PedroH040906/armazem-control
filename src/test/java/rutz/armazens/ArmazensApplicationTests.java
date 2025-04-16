package rutz.armazens;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import rutz.armazens.domain.Armazem;
import rutz.armazens.domain.Historico;
import rutz.armazens.domain.Produto;
import rutz.armazens.domain.Saldo;
import rutz.armazens.dtos.SaldoDTO;
import rutz.armazens.dtos.SaldoUpdateDTO;
import rutz.armazens.repository.ArmazemRepository;
import rutz.armazens.repository.HistoricoRepository;
import rutz.armazens.repository.ProdutoRepository;
import rutz.armazens.repository.SaldoRepository;
import rutz.armazens.services.SaldoService;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class ArmazensApplicationTests {

	@InjectMocks
	private SaldoService saldoService;

	@Mock
	private SaldoRepository repository;

	@Mock
	private ProdutoRepository produtoRepository;

	@Mock
	private ArmazemRepository armazemRepository;

	@Mock
	private HistoricoRepository historicoRepository;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void deveCriarNovoSaldoComSucesso() {
		Produto produto = new Produto();
		produto.setId(1L);
		Armazem armazem = new Armazem();
		armazem.setId(1L);

		SaldoDTO dto = new SaldoDTO();
		dto.setArmazem(armazem);
		dto.setProduto(produto);
		dto.setSaldo(10);

		when(repository.existsByProdutoIdAndArmazemId(1L, 1L)).thenReturn(false);
		when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));
		when(armazemRepository.findById(1L)).thenReturn(Optional.of(armazem));
		when(repository.save(any(Saldo.class))).thenAnswer(i -> i.getArguments()[0]);
		when(historicoRepository.save(any(Historico.class))).thenAnswer(i -> i.getArguments()[0]);

		var result = saldoService.criarSaldo(dto);
		assertNotNull(result);
		assertEquals(10, result.saldo());
	}

	@Test
	void deveLancarExcecaoQuandoSaldoNegativoOuZero() {
		SaldoDTO dto = new SaldoDTO();
		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			saldoService.criarSaldo(dto);
		});

		assertEquals("O saldo deve ser maior que zero.", exception.getMessage());
	}

	@Test
	void deveAdicionarSaldoComSucesso() {
		Produto produto = new Produto();
		produto.setId(1L);
		Armazem armazem = new Armazem();
		armazem.setId(1L);

		Saldo saldo = new Saldo();
		saldo.setId(1L);
		saldo.setSaldo(10);
		saldo.setProduto(produto);
		saldo.setArmazem(armazem);

		when(repository.findById(1L)).thenReturn(Optional.of(saldo));
		when(repository.save(any())).thenAnswer(i -> i.getArguments()[0]);
		when(historicoRepository.save(any())).thenAnswer(i -> i.getArguments()[0]);

		var dto = new SaldoUpdateDTO(5);

		var result = saldoService.adicicionarSaldo(1L, dto);
		assertEquals(15, result.saldo());
	}

	@Test
	void deveLancarErroAoAdicionarSaldoNegativo() {
		var dto = new SaldoUpdateDTO(0);
		when(repository.findById(1L)).thenReturn(Optional.of(new Saldo()));
		assertThrows(IllegalArgumentException.class, () -> {
			saldoService.adicicionarSaldo(1L, dto);
		});
	}



}
