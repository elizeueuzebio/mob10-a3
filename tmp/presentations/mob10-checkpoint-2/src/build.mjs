/** @jsxRuntime automatic */
/** @jsxImportSource @oai/artifact-tool/presentation-jsx */

import fs from "node:fs/promises";
import path from "node:path";
import { fileURLToPath } from "node:url";

const {
  Presentation,
  PresentationFile,
  row,
  column,
  grid,
  panel,
  text,
  shape,
  rule,
  image,
  fill,
  hug,
  fixed,
  wrap,
  fr,
  auto,
} = await import("@oai/artifact-tool");

const __filename = fileURLToPath(import.meta.url);
const __dirname = path.dirname(__filename);
const workspaceDir = path.resolve(__dirname, "..");
const scratchDir = path.join(workspaceDir, "scratch");
const outputDir = path.join(workspaceDir, "output");
const outputPptx = path.join(outputDir, "output.pptx");
const logoSourcePath = "C:/Users/elize/Pictures/WhatsApp Image 2026-04-27 at 22.42.55.jpeg";
const logoPath = path.join(scratchDir, "mob10-logo.jpeg");

const slideSize = { width: 1920, height: 1080 };

const colors = {
  bg: "#F6F1E8",
  paper: "#FFFDF9",
  ink: "#162338",
  muted: "#5E697A",
  accent: "#2D9C91",
  accentSoft: "#DDF2ED",
  accentDeep: "#1E6D66",
  warm: "#E4A14A",
  line: "#D8D0C4",
};

const fonts = {
  title: "Georgia",
  body: "Trebuchet MS",
};

async function saveBlob(filePath, blob) {
  const buffer = Buffer.from(await blob.arrayBuffer());
  await fs.writeFile(filePath, buffer);
}

async function hydrateLocalImages(presentation) {
  const pending = presentation.getPendingImageHydrationRequests();
  if (!pending.length) {
    return;
  }

  const hydrated = await Promise.all(
    pending.map(async (item) => ({
      assetId: item.assetId,
      contentType: item.contentType,
      data: await fs.readFile(item.uri),
    })),
  );

  presentation.hydrateImageAssets(hydrated);
}

function titleText(value, options = {}) {
  return text(value, {
    name: options.name,
    width: options.width ?? fill,
    height: hug,
    columnSpan: options.columnSpan,
    style: {
      fontFace: fonts.title,
      fontSize: options.size ?? 58,
      bold: options.bold ?? true,
      color: options.color ?? colors.ink,
    },
  });
}

function bodyText(value, options = {}) {
  return text(value, {
    name: options.name,
    width: options.width ?? fill,
    height: hug,
    columnSpan: options.columnSpan,
    style: {
      fontFace: fonts.body,
      fontSize: options.size ?? 24,
      color: options.color ?? colors.muted,
      bold: options.bold ?? false,
      wrap: options.nowrap ? "none" : undefined,
    },
  });
}

function eyebrow(value, options = {}) {
  return text(value, {
    name: options.name,
    width: options.width ?? fill,
    height: hug,
    columnSpan: options.columnSpan,
    style: {
      fontFace: fonts.body,
      fontSize: options.size ?? 18,
      bold: true,
      color: options.color ?? colors.accentDeep,
    },
  });
}

function pill(label, options = {}) {
  return panel(
    {
      name: options.name,
      width: hug,
      height: hug,
      padding: options.padding ?? { x: 18, y: 10 },
      fill: options.fill ?? colors.accentSoft,
      borderRadius: "rounded-full",
      align: "center",
      justify: "center",
    },
    text(label, {
      width: hug,
      height: hug,
      style: {
        fontFace: fonts.body,
        fontSize: options.size ?? 18,
        bold: true,
        color: options.color ?? colors.accentDeep,
        wrap: "none",
      },
    }),
  );
}

function stepBadge(value, options = {}) {
  return panel(
    {
      width: options.width ?? fixed(48),
      height: options.height ?? fixed(48),
      fill: options.fill ?? colors.accent,
      borderRadius: "rounded-full",
      align: "center",
      justify: "center",
    },
    text(String(value), {
      width: hug,
      height: hug,
      style: {
        fontFace: fonts.body,
        fontSize: options.size ?? 24,
        bold: true,
        color: "#FFFFFF",
      },
    }),
  );
}

function checkItem(value) {
  return row(
    { width: fill, height: hug, gap: 14, align: "center" },
    [
      shape({ geometry: "ellipse", width: fixed(14), height: fixed(14), fill: colors.accent }),
      bodyText(value, { size: 24, color: colors.ink }),
    ],
  );
}

function requirementStrip(number, heading, description) {
  return row(
    { width: fill, height: hug, gap: 18, align: "start" },
    [
      stepBadge(number, { width: fixed(46), height: fixed(46), size: 23 }),
      column({ width: fill, height: hug, gap: 4 }, [
        bodyText(heading, { size: 24, color: colors.ink, bold: true }),
        bodyText(description, { size: 21 }),
      ]),
    ],
  );
}

function packageColumn(name, description, classList) {
  return column({ width: fill, height: fill, gap: 18 }, [
    pill(name, { fill: colors.paper, color: colors.accentDeep, size: 20, padding: { x: 20, y: 12 } }),
    bodyText(description, { size: 22, color: colors.ink }),
    bodyText(classList, { size: 20 }),
  ]);
}

function flowNode(number, heading, description) {
  return column(
    { width: fixed(270), height: hug, gap: 14, align: "center", justify: "start" },
    [
      stepBadge(number, { width: fixed(56), height: fixed(56), size: 26, fill: colors.accentDeep }),
      bodyText(heading, { size: 22, color: colors.ink, bold: true, width: wrap(250), nowrap: true }),
      bodyText(description, { size: 19, width: wrap(250) }),
    ],
  );
}

function metric(number, label) {
  return column({ width: fill, height: hug, gap: 4 }, [
    titleText(number, { size: 68, color: colors.accentDeep }),
    bodyText(label, { size: 21, color: colors.ink }),
  ]);
}

function buildPresentation() {
  const presentation = Presentation.create({ slideSize });

  const cover = presentation.slides.add();
  cover.background.fill = colors.bg;
  cover.compose(
    grid(
      {
        name: "cover-root",
        width: fill,
        height: fill,
        columns: [fr(1.18), fr(0.82)],
        rows: [auto, fr(1), auto],
        columnGap: 54,
        rowGap: 30,
        padding: { x: 96, y: 82 },
      },
      [
        column({ name: "cover-title", width: fill, height: hug, gap: 16 }, [
          eyebrow("A3 | Algoritmos | Checkpoint 2"),
          titleText("Mob10", { size: 94 }),
          bodyText("Estrutura parcial do sistema em Java para um aplicativo de transporte acessível.", {
            size: 30,
            width: wrap(840),
            color: colors.ink,
          }),
        ]),
        column(
          {
            name: "cover-route",
            width: fill,
            height: fill,
            gap: 20,
            justify: "center",
            rowSpan: 3,
          },
          [
            image({
              name: "mob10-logo",
              path: logoPath,
              width: fixed(430),
              height: fixed(430),
              fit: "contain",
              alt: "Logo do Mob10",
            }),
            row({ width: fill, height: hug, gap: 18, align: "center" }, [
              stepBadge("1"),
              column({ width: fill, height: hug, gap: 4 }, [
                bodyText("Cadastro dos usuários", { size: 24, color: colors.ink, bold: true }),
                bodyText("Passageiro e motorista com encapsulamento completo.", { size: 19 }),
              ]),
            ]),
            shape({ width: fixed(4), height: fixed(42), fill: colors.accentSoft }),
            row({ width: fill, height: hug, gap: 18, align: "center" }, [
              stepBadge("2", { fill: colors.warm }),
              column({ width: fill, height: hug, gap: 4 }, [
                bodyText("Solicitação e match", { size: 24, color: colors.ink, bold: true }),
                bodyText("Fluxo básico de corrida com motorista compatível.", { size: 19 }),
              ]),
            ]),
            shape({ width: fixed(4), height: fixed(42), fill: colors.accentSoft }),
            row({ width: fill, height: hug, gap: 18, align: "center" }, [
              stepBadge("3", { fill: colors.accentDeep }),
              column({ width: fill, height: hug, gap: 4 }, [
                bodyText("Pagamento e avaliação", { size: 24, color: colors.ink, bold: true }),
                bodyText("Serviços com ArrayList e demonstração executável.", { size: 19 }),
              ]),
            ]),
          ],
        ),
        column({ width: fill, height: hug, gap: 18 }, [
          bodyText(
            "Projeto criado para reduzir a dificuldade de locomoção de cadeirantes, idosos e pessoas em recuperação médica, com foco em autonomia, inclusão e previsibilidade do transporte.",
            {
              size: 26,
              width: wrap(930),
            },
          ),
          row({ width: fill, height: hug, gap: 12 }, [
            pill("Mobilidade reduzida"),
            pill("Acessibilidade"),
            pill("Código organizado"),
          ]),
        ]),
        column({ width: fill, height: hug, gap: 8 }, [
          bodyText("Integrantes", { size: 18, color: colors.accentDeep, bold: true }),
          bodyText("Elizeu Martins Euzebio | Enrique Salviato Sanchez", { size: 20, color: colors.ink }),
        ]),
      ],
    ),
    { frame: { left: 0, top: 0, width: slideSize.width, height: slideSize.height }, baseUnit: 8 },
  );

  const problem = presentation.slides.add();
  problem.background.fill = colors.bg;
  problem.compose(
    grid(
      {
        name: "problem-root",
        width: fill,
        height: fill,
        columns: [fr(1.08), fr(0.92)],
        rows: [auto, fr(1)],
        columnGap: 58,
        rowGap: 28,
        padding: { x: 96, y: 76 },
      },
      [
        column({ width: fill, height: hug, gap: 14, columnSpan: 2 }, [
          eyebrow("Contexto do projeto"),
          titleText("Problema e proposta de solução", { size: 64 }),
        ]),
        column({ width: fill, height: fill, gap: 24 }, [
          titleText("Transporte acessível não pode depender de improviso.", {
            size: 48,
            width: wrap(820),
            color: colors.ink,
          }),
          bodyText(
            "O Mob10 foi pensado como um aplicativo semelhante ao Uber, porém totalmente voltado para pessoas com mobilidade reduzida. A ideia central é conectar passageiros a motoristas e veículos compatíveis com suas necessidades.",
            {
              size: 25,
              width: wrap(820),
            },
          ),
          row({ width: fill, height: hug, gap: 12 }, [
            pill("Cadeirantes"),
            pill("Idosos"),
            pill("Recuperação médica"),
          ]),
        ]),
        column({ width: fill, height: fill, gap: 18 }, [
          bodyText("Quem é impactado positivamente", { size: 22, color: colors.accentDeep, bold: true }),
          checkItem("Passageiros com mais autonomia e segurança."),
          checkItem("Motoristas parceiros com nova fonte de renda."),
          checkItem("Sociedade com maior inclusão social."),
          rule({ width: fixed(320), stroke: colors.line, weight: 2 }),
          bodyText("Valor da proposta", { size: 22, color: colors.accentDeep, bold: true }),
          bodyText(
            "Cadastro de usuários, registro de veículos adaptados, solicitação de corridas, pagamento e avaliação em uma única base orientada a objetos.",
            {
              size: 22,
            },
          ),
        ]),
      ],
    ),
    { frame: { left: 0, top: 0, width: slideSize.width, height: slideSize.height }, baseUnit: 8 },
  );

  const scope = presentation.slides.add();
  scope.background.fill = colors.bg;
  scope.compose(
    grid(
      {
        name: "scope-root",
        width: fill,
        height: fill,
        columns: [fr(1.05), fr(0.95)],
        rows: [auto, fr(1)],
        columnGap: 56,
        rowGap: 26,
        padding: { x: 96, y: 76 },
      },
      [
        column({ width: fill, height: hug, gap: 14, columnSpan: 2 }, [
          eyebrow("Entrega mínima"),
          titleText("Objetivo do checkpoint 2", { size: 64 }),
        ]),
        column({ width: fill, height: fill, gap: 22 }, [
          titleText("Entregar a base técnica pronta para crescer.", {
            size: 46,
            width: wrap(820),
          }),
          requirementStrip("1", "Estrutura em pacotes", "Organização do projeto em model, service e view."),
          requirementStrip("2", "Orientação a objetos", "Atributos privados, construtores, getters, setters e toString()."),
          requirementStrip("3", "Serviços com listas", "Uso de ArrayList para armazenar e manipular os objetos do sistema."),
          requirementStrip("4", "Documentação inicial", "README.md com descrição do projeto e instruções básicas de execução."),
        ]),
        column({ width: fill, height: fill, gap: 20 }, [
          bodyText("Formato de entrega", { size: 22, color: colors.accentDeep, bold: true }),
          bodyText("Repositório GitHub com código parcial e link enviado no Ulife.", {
            size: 28,
            width: wrap(620),
            color: colors.ink,
          }),
          rule({ width: fixed(360), stroke: colors.line, weight: 2 }),
          bodyText("Resultado alcançado", { size: 22, color: colors.accentDeep, bold: true }),
          bodyText(
            "Projeto compilável, pacotes organizados, classes do domínio implementadas e uma Main demonstrando o fluxo inicial do sistema.",
            {
              size: 24,
              width: wrap(620),
            },
          ),
          row({ width: fill, height: hug, gap: 12 }, [
            pill("Código parcial"),
            pill("Base pronta"),
            pill("Expansão no checkpoint 3"),
          ]),
        ]),
      ],
    ),
    { frame: { left: 0, top: 0, width: slideSize.width, height: slideSize.height }, baseUnit: 8 },
  );

  const architecture = presentation.slides.add();
  architecture.background.fill = colors.bg;
  architecture.compose(
    grid(
      {
        name: "architecture-root",
        width: fill,
        height: fill,
        columns: [fr(1), fr(1), fr(1)],
        rows: [auto, fr(1), auto],
        columnGap: 34,
        rowGap: 26,
        padding: { x: 96, y: 76 },
      },
      [
        column({ width: fill, height: hug, gap: 14, columnSpan: 3 }, [
          eyebrow("Organização do código"),
          titleText("Arquitetura implementada", { size: 64 }),
        ]),
        packageColumn(
          "model",
          "Camada responsável pelas entidades centrais do sistema.",
          "Usuario\nMotorista\nPassageiro\nVeiculo\nCarroComum\nCarroAdaptado\nVan\nCorrida\nPagamento\nAvaliacao\nRegiao",
        ),
        packageColumn(
          "service",
          "Camada com regras básicas e armazenamento em listas.",
          "UsuarioService\nCorridaService\nPagamentoService\nAvaliacaoService",
        ),
        packageColumn(
          "view",
          "Camada de execução inicial para demonstrar o funcionamento.",
          "Main\n\nCadastro\nSolicitação de corrida\nMatch de motorista\nPagamento\nAvaliação",
        ),
        column({ width: fill, height: hug, gap: 12, columnSpan: 3 }, [
          rule({ width: fill, stroke: colors.line, weight: 2 }),
          bodyText(
            "Heranças implementadas: Usuario -> Motorista e Passageiro | Veiculo -> CarroComum, CarroAdaptado e Van.",
            {
              size: 22,
              color: colors.ink,
            },
          ),
        ]),
      ],
    ),
    { frame: { left: 0, top: 0, width: slideSize.width, height: slideSize.height }, baseUnit: 8 },
  );

  const flow = presentation.slides.add();
  flow.background.fill = colors.bg;
  flow.compose(
    column(
      {
        name: "flow-root",
        width: fill,
        height: fill,
        padding: { x: 96, y: 76 },
        gap: 34,
      },
      [
        column({ width: fill, height: hug, gap: 14 }, [
          eyebrow("Demonstração atual"),
          titleText("Fluxo básico já validado na aplicação", { size: 64 }),
        ]),
        row(
          {
            width: fill,
            height: hug,
            gap: 16,
            align: "center",
            justify: "start",
          },
          [
            flowNode("1", "Cadastro", "Usuários e região são criados com construtores."),
            shape({ width: fixed(42), height: fixed(4), fill: colors.accentSoft }),
            flowNode("2", "Solicitação", "O passageiro solicita a corrida pelo serviço."),
            shape({ width: fixed(42), height: fixed(4), fill: colors.accentSoft }),
            flowNode("3", "Match", "Um motorista compatível aceita a corrida."),
            shape({ width: fixed(42), height: fixed(4), fill: colors.accentSoft }),
            flowNode("4", "Pagamento", "O valor é processado no PagamentoService."),
            shape({ width: fixed(42), height: fixed(4), fill: colors.accentSoft }),
            flowNode("5", "Avaliação", "O passageiro registra nota e comentário."),
          ],
        ),
        bodyText(
          "Esse fluxo está representado na Main, que compila e executa sem erros, servindo como prova funcional do checkpoint 2.",
          {
            size: 24,
            color: colors.ink,
            width: wrap(1500),
          },
        ),
      ],
    ),
    { frame: { left: 0, top: 0, width: slideSize.width, height: slideSize.height }, baseUnit: 8 },
  );

  const checklist = presentation.slides.add();
  checklist.background.fill = colors.bg;
  checklist.compose(
    grid(
      {
        name: "checklist-root",
        width: fill,
        height: fill,
        columns: [fr(1.02), fr(0.98)],
        rows: [auto, fr(1)],
        columnGap: 58,
        rowGap: 28,
        padding: { x: 96, y: 76 },
      },
      [
        column({ width: fill, height: hug, gap: 14, columnSpan: 2 }, [
          eyebrow("Validação do checkpoint"),
          titleText("Checklist atendido no código", { size: 64 }),
        ]),
        column({ width: fill, height: fill, gap: 18 }, [
          checkItem("Pacotes model, service e view criados."),
          checkItem("Atributos privados em todas as classes."),
          checkItem("Construtores para inicialização dos objetos."),
          checkItem("Getters e setters implementados."),
          checkItem("Método toString() sobrescrito."),
          checkItem("ArrayList declarado nos serviços."),
          checkItem("README.md com descrição do projeto."),
          checkItem("Main funcional para demonstração."),
        ]),
        column({ width: fill, height: fill, gap: 22 }, [
          bodyText("Evidências rápidas", { size: 22, color: colors.accentDeep, bold: true }),
          grid(
            {
              width: fill,
              height: hug,
              columns: [fr(1), fr(1)],
              rows: [auto, auto],
              columnGap: 28,
              rowGap: 18,
            },
            [
              metric("11", "classes de model"),
              metric("4", "services com ArrayList"),
              metric("3", "pacotes principais"),
              metric("1", "fluxo executável na Main"),
            ],
          ),
          rule({ width: fixed(420), stroke: colors.line, weight: 2 }),
          bodyText("Observação operacional", { size: 22, color: colors.accentDeep, bold: true }),
          bodyText(
            "README pronto e repositório local inicializado. Para a entrega final, falta apenas publicar o remoto GitHub em modo público e enviar o link.",
            {
              size: 22,
              width: wrap(640),
            },
          ),
        ]),
      ],
    ),
    { frame: { left: 0, top: 0, width: slideSize.width, height: slideSize.height }, baseUnit: 8 },
  );

  const nextSteps = presentation.slides.add();
  nextSteps.background.fill = colors.bg;
  nextSteps.compose(
    grid(
      {
        name: "next-root",
        width: fill,
        height: fill,
        columns: [fr(1), fr(1), fr(1)],
        rows: [auto, fr(1), auto],
        columnGap: 34,
        rowGap: 30,
        padding: { x: 96, y: 76 },
      },
      [
        column({ width: fill, height: hug, gap: 14, columnSpan: 3 }, [
          eyebrow("Continuidade do projeto"),
          titleText("Próximos passos para o checkpoint 3", { size: 64 }),
        ]),
        column({ width: fill, height: fill, gap: 16 }, [
          stepBadge("1", { fill: colors.accentDeep }),
          bodyText("Regras de negócio", { size: 24, color: colors.ink, bold: true }),
          bodyText("Expandir validações, status de corrida, cálculo de valor e critérios de compatibilidade.", { size: 22 }),
        ]),
        column({ width: fill, height: fill, gap: 16 }, [
          stepBadge("2", { fill: colors.warm }),
          bodyText("Interface de uso", { size: 24, color: colors.ink, bold: true }),
          bodyText("Criar menus, entradas de dados e interações mais completas para demonstrar o sistema.", { size: 22 }),
        ]),
        column({ width: fill, height: fill, gap: 16 }, [
          stepBadge("3", { fill: colors.accent }),
          bodyText("Entrega final", { size: 24, color: colors.ink, bold: true }),
          bodyText("Publicar no GitHub público, evoluir o código e preparar a apresentação final do projeto.", { size: 22 }),
        ]),
        column({ width: fill, height: hug, gap: 12, columnSpan: 3 }, [
          rule({ width: fill, stroke: colors.line, weight: 2 }),
          titleText("Checkpoint 2 concluído com base estruturada, compilável e pronta para evolução.", {
            size: 34,
            color: colors.accentDeep,
          }),
        ]),
      ],
    ),
    { frame: { left: 0, top: 0, width: slideSize.width, height: slideSize.height }, baseUnit: 8 },
  );

  return presentation;
}

async function exportPresentation(presentation) {
  await fs.mkdir(scratchDir, { recursive: true });
  await fs.mkdir(outputDir, { recursive: true });
  await fs.copyFile(logoSourcePath, logoPath);
  await hydrateLocalImages(presentation);

  const pptxBlob = await PresentationFile.exportPptx(presentation);
  await pptxBlob.save(outputPptx);

  for (let index = 0; index < presentation.slides.items.length; index += 1) {
    const slide = presentation.slides.getItem(index);
    const previewBlob = await slide.export({ format: "png" });
    const layoutBlob = await slide.export({ format: "layout" });

    await saveBlob(path.join(scratchDir, `source-slide-${index + 1}.png`), previewBlob);
    await saveBlob(path.join(scratchDir, `source-slide-${index + 1}.layout.json`), layoutBlob);
  }

  const pptxBytes = await fs.readFile(outputPptx);
  const imported = await PresentationFile.importPptx(pptxBytes);

  for (let index = 0; index < imported.slides.items.length; index += 1) {
    const slide = imported.slides.getItem(index);
    const previewBlob = await slide.export({ format: "png" });
    const layoutBlob = await slide.export({ format: "layout" });

    await saveBlob(path.join(scratchDir, `pptx-slide-${index + 1}.png`), previewBlob);
    await saveBlob(path.join(scratchDir, `pptx-slide-${index + 1}.layout.json`), layoutBlob);
  }
}

const presentation = buildPresentation();
await exportPresentation(presentation);

console.log(JSON.stringify({
  pptx: outputPptx,
  slides: presentation.slides.items.length,
  scratch: scratchDir,
}, null, 2));
