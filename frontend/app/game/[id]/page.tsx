export default async function GamePage({
	params,
}: {
	params: Promise<{ id: string }>
}) {
	const { id } = await params
	return <h1 className="text-indigo-500">Game n°{id}</h1>
}